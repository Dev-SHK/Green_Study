package org.comstudy21.ex06;

import myframe.MyJFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import static org.comstudy21.ex06.R.*;

public class TestJTable extends MyJFrame {


    public TestJTable() {
        super("JTable 연습", 1280, 720);
        super.setTitle("회원 정보 관리 프로그램");
    }

    private void tableData() {
        columnNames = new Vector();
        columnNames.add("번호");
        columnNames.add("이름");
        columnNames.add("지역");
        columnNames.add("성별");
        columnNames.add("메일");
        columnNames.add("전화");

        // Object[][] 배열을 태체하는 코드
        data = dao.selectAll();

    }

    @Override
    protected void displayLayer() {
        tableData();
        contentPan = getContentPane();

        tbModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tbModel);
        scrollPane = new JScrollPane(table);

        contentPan.add(scrollPane);
        contentPan.add(BorderLayout.WEST, new LeftPane());
        contentPan.add(BorderLayout.SOUTH, new BottomPane());
        contentPan.add(BorderLayout.NORTH, new TopPane());
        contentPan.setBackground(lavender01);
    }

    protected void actionEvent() {
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(">> 마우스를 누름");
                JTable tbl = (JTable) e.getSource();
                int totalCol = tbl.getColumnCount();
                int totalRow = tbl.getRowCount();
//                System.out.printf("%d행 %d열\n", totalCol, totalRow);
                // 선택한 컬럼의 행과 열 알아내기
                int col = table.getSelectedColumn();
                int row = table.getSelectedRow();
//                System.out.printf("%d행, %d열\n", col, row);

                // 데이터 찾아오기
                int idx = (int) tbModel.getValueAt(row, 0);
                String name = (String) tbModel.getValueAt(row, 1);
                String location = (String) tbModel.getValueAt(row, 2);
                String gender = (String) tbModel.getValueAt(row, 3);
                String email = (String) tbModel.getValueAt(row, 4);
                String phone = (String) tbModel.getValueAt(row, 5);
//                System.out.printf("%d, %s, %s, %s\n", idx, name, email, phone);

                // 찾아온 데이터 적용하기
                txtFld.setText("" + idx);
                txtFld01.setText(name);
                txtFld02.setText(email);
                txtFld03.setText(phone);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        // 버튼 이벤트 핸들러 추가
        allBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayList();
                JOptionPane.showMessageDialog(null, "데이터를 모두 불러왔어요.", "불러오기 완료", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        inputBtn.addActionListener(new ActionListener() {
            String gender = "";

            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtFld.getText();
                txtFld.setText("");
                String name = txtFld01.getText();
                txtFld01.setText("");
                String email = txtFld02.getText();
                txtFld02.setText("");
                String phone = txtFld03.getText();
                txtFld03.setText("");
                if (male.isSelected()) {
                    gender = male.getText();
                }
                if (female.isSelected()) {
                    gender = female.getText();
                }
                String location = loCombo.getSelectedItem().toString();
                // dao에 저장 후
                int confirm = JOptionPane.showConfirmDialog(null, "입력하시겠습니까?", "입력", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (name.equals("") || email.equals("") || phone.equals("")) {
                        JOptionPane.showMessageDialog(null, "입력값이 없습니다. 다시 입력해주세요.", "입력값 없음", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "입력되었어요.", "입력완료", JOptionPane.INFORMATION_MESSAGE);
                        dao.insert(new SaramDTO(0, name, location, gender, email, phone));

                        // TextField의 데이터를 읽어온다

//                System.out.printf("id => %s\n", id);
//                System.out.printf("name => %s\n", name);
//                System.out.printf("email => %s\n", email);
//                System.out.printf("phone => %s\n", phone);

                        // list를 다시 그려 준다
                        displayList();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "취소되었어요.", "취소", JOptionPane.INFORMATION_MESSAGE);
//                    dao.delete(new SaramDTO(0, name, gender, email, phone));
//                    System.out.println(new SaramDTO());
                }
            }
        });

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtFld01.getText();
                Vector<Vector> saramList = dao.selectList(new SaramDTO(0, name, null, null, null, null));
                tbModel.setDataVector(null, columnNames);
                for (Vector vector : saramList) {
                    tbModel.addRow(vector);
                }
                JOptionPane.showMessageDialog(null, "검색되었어요.", "검색완료", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        modifyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gender = "";
                int confirm = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?", "수정", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "수정되었어요.", "수정완료", JOptionPane.INFORMATION_MESSAGE);
                    int row = table.getSelectedRow();
                    int idx = Integer.parseInt(txtFld.getText());
                    String name = txtFld01.getText();
                    String email = txtFld02.getText();
                    String phone = txtFld03.getText();
                    if (male.isSelected()) {
                        gender = male.getText();
                    }
                    if (female.isSelected()) {
                        gender = female.getText();
                    }
                    String location = loCombo.getSelectedItem().toString();
                    dao.modify(new SaramDTO(idx, name, location, gender, email, phone));
                    displayList();
                } else {
                    JOptionPane.showMessageDialog(null, "취소되었어요.", "취소", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "삭제되었어요.", "삭제완료", JOptionPane.INFORMATION_MESSAGE);
                    String name = txtFld01.getText();
                    dao.delete(new SaramDTO(0, name, null, null, null, null));
                    displayList();
                } else {
                    JOptionPane.showMessageDialog(null, "취소되었어요.", "취소", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        finBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "종료합니다. 이용해 주셔서 고맙습니다.", "종료", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "취소되었습니다.", "취소", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


    }

    static void displayList() {
        tbModel.setDataVector(null, columnNames);
        Vector<Vector> saramList = dao.selectAll();
        for (Vector vector : saramList) {
            tbModel.addRow(vector);
        }
    }

    protected void addRowData() {
        tbModel.setDataVector(null, columnNames);
        tbModel.addRow(new Object[]{4, "LEE ", "MALE", "LEE-@L.COM", "010-4444-4444"});
        tbModel.addRow(new Object[]{5, "PARK", "FEMALE", "PARK@P.COM", "010-5555-5555"});
    }

    public static void main(String[] args) {
        new TestJTable().setVisible(true);
    }
}
