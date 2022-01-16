package org.comstudy21.ex06;

import javax.swing.*;
import java.awt.*;

import static org.comstudy21.ex06.R.*;

public class LeftPane extends JPanel {
    JPanel leftPan = new JPanel();
    JPanel centerPan = new JPanel(new GridBagLayout());
    public JLabel imgLbl = null;


    ButtonGroup bgroup = new ButtonGroup();

    Image img;
    String imgPath = "/Volumes/SHK-USB-64G/KDT-SW개발자과정/강의실습/Day23/src/org/comstudy21/ex06/★코로나19+행동수칙+메인+포스터(통합본)_국문_가로형_세로형_키오스크용/0225 코로나19 행동수칙 포스터(통합본)_국문_키오스크용/0225 코로나19 행동수칙 포스터(통합본)_국문_키오스크용.jpg";
    ImageIcon icon = new ImageIcon(imgPath);


    public LeftPane() {
//        this.add(new JLabel("::::: Input Data :::::"));
        bgroup.add(male);
        bgroup.add(female);


        add(BorderLayout.WEST, leftPan);
        add(BorderLayout.CENTER, centerPan);

        JPanel rowPan01 = new JPanel();
        rowPan01.add(new JLabel("번호"));
        rowPan01.add(txtFld);
        JPanel rowPan02 = new JPanel();
        rowPan02.add(new JLabel("이름"));
        rowPan02.add(txtFld01);
        JPanel rowPan03 = new JPanel();
        rowPan03.add(new JLabel("메일"));
        rowPan03.add(txtFld02);
        JPanel rowPan04 = new JPanel();
        rowPan04.add(new JLabel("전화"));
        rowPan04.add(txtFld03);
        JPanel rowPan05 = new JPanel();
        rowPan05.add(male);
        rowPan05.add(female);
        JPanel rowPan06 = new JPanel();
        rowPan06.add(loCombo);

        JPanel gridPan = new JPanel(new GridLayout(6, 1));
        gridPan.add(rowPan01);
        gridPan.add(rowPan02);
        gridPan.add(rowPan03);
        gridPan.add(rowPan04);
        gridPan.add(rowPan05);
        gridPan.add(rowPan06);

        leftPan.add(gridPan);

        img = icon.getImage();
        Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        imgLbl = new JLabel(icon);

    }

    public static void main(String[] args) {
        new LeftPane().setVisible(true);

    }
}
