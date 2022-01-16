package org.comstudy21.ex06;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class R {

    public static Container contentPan;
    public static JTable table;
    public static DefaultTableModel tbModel;
    public static JScrollPane scrollPane;

    public static Vector data;
    public static Vector<String> columnNames;
    public static Color lavender = new Color(204, 173, 238);
    public static Color lavender01 = new Color(232, 214, 250);


    public static final JTextField txtFld = new JTextField(15);
    public static final JTextField txtFld01 = new JTextField(15);
    public static final JTextField txtFld02 = new JTextField(15);
    public static final JTextField txtFld03 = new JTextField(15);
    public static final JTextField txtFld04 = new JTextField(15);
    public static final JRadioButton jRButton = new JRadioButton();
    public static final JRadioButton male = new JRadioButton("남자");
    public static final JRadioButton female = new JRadioButton("여자");
    public static final String[] location = {"서울", "부산", "대구", "인천", "광주", "대전", "울산", "세종", "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주"};
    public static final JComboBox<String> loCombo = new JComboBox<>(location);
    ;


    public static final JLabel subject = new JLabel("◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎ 회원 정보 관리 ◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎");
    public static final JButton allBtn = new JButton("모두보기");
    public static final JButton inputBtn = new JButton("입력하기");
    public static final JButton searchBtn = new JButton("검색하기");
    public static final JButton modifyBtn = new JButton("수정하기");
    public static final JButton delBtn = new JButton("삭제하기");
    public static final JButton finBtn = new JButton("종료하기");

    public static final SaramDAO dao = new SaramDAO();

}
