package org.comstudy21.ex06;

import java.util.Vector;

public class SaramDAO {

    // 일관성을 유지하기 위해서 이부분에 작성
    private static Vector<SaramDTO> saramList = new Vector<>();

    static {
        saramList.add(new SaramDTO(1, "김아무개", "서울", "남자", "KIM-@K.COM", "010-1234-1234"));
        saramList.add(new SaramDTO(2, "이아무개", "경기", "남자", "LEE-@L.COM", "010-2345-2345"));
        saramList.add(new SaramDTO(3, "박아무개", "부산", "여자", "PARK@P.COM", "010-3456-3456"));
    }

    public static int sequence = 4;

    public Vector selectAll() {
        Vector vector = new Vector();
        for (int i = 0; i < saramList.size(); i++) {
            vector.add(saramList.get(i).toVector()); // 최종적인 구조는 Vector 안에 Vector가 들어감
        }
        return vector;
    }

    public Vector selectList(SaramDTO saramDTO) {
        Vector vector = new Vector();
        for (int i = 0; i < saramList.size(); i++) {
            if (saramDTO.getName().equals(saramList.get(i).getName())) {
                vector.add(saramList.get(i).toVector());
            }
        }
        return vector;
    }

    public void insert(SaramDTO saramDTO) {
        if (saramDTO != null) {
            saramDTO.setIdx(sequence++);
            saramList.add(saramDTO);
        }
    }

    public Vector search(SaramDTO saramDTO) {
        Vector vector;
        for (int i = 0; i < saramList.size(); i++) {
            if (saramDTO.getName().equals(saramList.get(i).getName())) {
                vector = saramList.get(i).toVector();
                return vector;
            }
        }
        return null;
    }

    public void modify(SaramDTO saramDTO) {
        for (int i = 0; i < saramList.size(); i++) {
            if (saramDTO.getIdx() == saramList.get(i).getIdx()) {
                saramList.set(i, saramDTO);
            }
        }
    }

    public void delete(SaramDTO saramDTO) {
        for (int i = 0; i < saramList.size(); i++) {
            if (saramDTO.getName().equals(saramList.get(i).getName())) {
                saramList.remove(i);
            }
        }
    }
}
