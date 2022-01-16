package org.comstudy21.ex06;

import javax.swing.*;

import static org.comstudy21.ex06.R.*;

public class BottomPane extends JPanel {

    public BottomPane() {
        this.add(allBtn);
        this.add(inputBtn);
        this.add(searchBtn);
        this.add(modifyBtn);
        this.add(delBtn);
        this.add(finBtn);
        this.setBackground(lavender);
    }
}
