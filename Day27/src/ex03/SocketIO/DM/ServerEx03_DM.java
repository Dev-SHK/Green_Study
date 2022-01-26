package ex03.SocketIO.DM;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class ServerEx03_DM extends Thread {
    class User {
        Socket socket;
        BufferedReader br;
        PrintWriter pw;

        public User(Socket socket, BufferedReader br, PrintWriter pw) {
            this.socket = socket;
            this.br = br;
            this.pw = pw;
        }
    }

    HashMap<String, User> userMap = new HashMap<>();
    ServerSocket listener = null;
    Socket socket = null;
    Scanner scan = new Scanner(System.in);

    public ServerEx03_DM() {
        try {
            // ServerSocket을 생성하고
            listener = new ServerSocket(9000); // 모니터 - URL의 제일 끝단 (End Pointer)
            System.out.println("서버 >>> 서버 대기중 ...");
            // 클라이언트 접속 대기 - 접속이 되면 Socket을 반환한다. (쓰레드에서 대기 중)

            // 쓰레드 실행 순서가 중요하다.
            this.start();

            // 서버에서 임의 메세지 입력 기능
            while (true) {
                String line = scan.nextLine();
                broadcast(String.format("Server>>> %s\n", line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        // 새로운 사용자가 들어오는지 체크하는 쓰레드
        String line = null;
        while (true) {
            acceptSocket();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // DM 기능 (귓속말, Direct Message)
    private void directMessage(String dm) {
        PrintWriter pw = null;
        int begin, end;
        String id, msg;
        // ID 찾기
        begin = dm.indexOf(" ") + 1; // 첫번째 공백 그 다음
        end = dm.indexOf(" ", begin); // 변수 begin 값부터 공백이 나올때까지

        if (end != -1) {
            id = dm.substring(begin, end); // ID
            msg = dm.substring(end + 1); // ID의 end번째 index 다음부터가 메시지
            Object obj = userMap.get(id).pw;
            if (obj != null) {
                pw = (PrintWriter) obj;
                pw.println(id + "님의 DM : " + msg + "\n");
                pw.flush();
            }
        }
    }

    private void acceptSocket() {
        try {
            socket = listener.accept();
            // 클라이언트와 입,출력 스트림을 연결한다.
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 메세지 받는 쓰레드 실행
            try {
                // 라인의 '\n'이다. '\n'이 없는 데이터는 readLine()로 읽을 수 없다.
                String userId = br.readLine();
                System.out.println("서버 >>> " + userId + "님이 접속 하였습니다!");
                // userId가 있고 pw가 있다면 사용자를 map 추가한다.
                userMap.put(userId, new User(socket, br, pw));
                broadcast(">> " + userId + "님이 입장하셨습니다.");

                ReceiveThread receive = new ReceiveThread(br, userId);
                receive.start();
            } catch (Exception e) {
                System.out.println("사용자 소켓 생성 예외 발생!");
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void broadcast(String message) {
        // userMap에 저장된 모든 사용자들에게 메세지를 전달한다.
        Iterator<String> keys = userMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            User user = userMap.get(key);
            Socket socket = user.socket;
            PrintWriter out = user.pw;
            out.println(new String(message.getBytes(), StandardCharsets.UTF_8));
            out.flush();
        }
    }

    public static void main(String[] args) {
        // Socket Server
        new ServerEx03_DM();
    }

    // ---- 내부 클래스 - 메세지를 받는 쓰레드 선언
    class ReceiveThread extends Thread {
        // 연결된 소켓과의 입력 스트립 객체
        BufferedReader in = null;
        String userId = "";

        public ReceiveThread(BufferedReader br, String userId) {
            this.in = br;
        }

        @Override
        public void run() {
            while (in != null) {
                try {
                    String clientMessage = in.readLine();
                    if ("/exit".equalsIgnoreCase(clientMessage)) {
                        System.out.println("/exit 입력되어서 끝낸다!");
                        System.exit(0);
                        break;
                    }
                    if (clientMessage.indexOf("/to") == 0) {
//                        System.out.println("쪽지");
                        directMessage(clientMessage);
                    } else {
//                        System.out.println("공개메시지");
                        broadcast(clientMessage);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    } // end of ReceiveThread
}