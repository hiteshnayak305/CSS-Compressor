/*
 * A java application to compress css code to prepare it for being uploaded to server
 * Since transfer of bits of data affect the speed of loading webpage
 * So it is necessary to compress css files to decrease its size...
 */
package css.compressor;

import java.awt.*;
import java.awt.event.*;

/**
 * @author hitesh
 * Uploaded to : https://github.com/hiteshnayak305 repository=CSS Compressor
 */
public class CSSCompressor extends Frame implements ActionListener {

    //containers
    Panel center;
    Panel north;
    //declare Layouts
    BorderLayout b;
    GridLayout g;
    GridLayout g2;

    //declare menubar and items
    MenuBar mb;
    //Menu
    Menu file, edit, view, help;
    //Menuitems
    //file
    MenuItem nw, opn, sv, ext;
    //edit
    MenuItem cut, cpy, pst, slctAll;
    //view
    CheckboxMenuItem fullscr;
    CheckboxMenuItem stbarview;
    //help

    //buttons
    Button com,clr;
    //TextArea
    TextArea mainTxt;
    TextArea comTxt;
    //string buffer
    String cp;

    //statusbar
    Label stbar;

    /**
     *
     */
    public CSSCompressor() {
        super("CSS Compressor");
        setSize(700, 500);
        setBackground(new Color(250, 250, 250));
        setVisible(true);
        cp = "";
        //setting Layout
        b = new BorderLayout(10, 5);  //hsp,vsp
        setLayout(b);
        g = new GridLayout(2,1,10,5);
        center = new Panel(g);
        g2 = new GridLayout(1,10,10,5);
        north = new Panel(g2);
        //Play background audio
        //AudioClip ac;
        //ac = getAudioClip(getDocumentBase(),"a.au");
        //ac.loop();

        /****	  set menu bar        ****/
        mb = new MenuBar();
        setMenuBar(mb);

        //set file menu
        file = new Menu("File");
        mb.add(file);
        //set menu items
        nw = new MenuItem("New");
        nw.addActionListener(this);
        file.add(nw);
        opn = new MenuItem("Open");
        opn.addActionListener(this);
        file.add(opn);
        sv = new MenuItem("Save");
        sv.addActionListener(this);
        file.add(sv);
        MenuItem sprtr = new MenuItem("-");
        file.add(sprtr);
        ext = new MenuItem("Exit");
        ext.addActionListener(this);
        file.add(ext);

        //set edit menu
        edit = new Menu("Edit");
        mb.add(edit);
        //set menuitems
        cut = new MenuItem("Cut");
        cut.addActionListener(this);
        edit.add(cut);
        cpy = new MenuItem("Copy");
        cpy.addActionListener(this);
        edit.add(cpy);
        pst = new MenuItem("Paste");
        pst.addActionListener(this);
        edit.add(pst);
        slctAll = new MenuItem("Select All");
        slctAll.addActionListener(this);
        edit.add(slctAll);

        //set view menu
        view = new Menu("View");
        mb.add(view);
        //set menu items
        fullscr = new CheckboxMenuItem("Show full screen");
        view.add(fullscr);
        stbarview = new CheckboxMenuItem("Show Status Bar");
        view.add(stbarview);
        //set help menu

        //buttons
        com = new Button("Compress");
        com.addActionListener(this);
        clr = new Button("Clear");
        clr.addActionListener(this);
        north.add(com);
        north.add(clr);
        north.add(new Label());
        north.add(new Label());
        north.add(new Label());
        north.add(new Label());
        north.add(new Label());
        north.add(new Label());
        north.add(new Label());
        add(north,BorderLayout.NORTH);
        
        /****       main workspace       ****/
        String init = "class c{\n\tpublic static void main(){\n\t\tSystem.out.println(\"Hello World!\");\n\t}\n}";
        //textArea
        mainTxt = new TextArea(init, 700, 240, TextArea.SCROLLBARS_BOTH);
        center.add(mainTxt);
        
        comTxt = new TextArea("", 700, 240, TextArea.SCROLLBARS_VERTICAL_ONLY);
        comTxt.setEditable(false);
        center.add(comTxt);
        add(center,BorderLayout.CENTER);
       
        /****         status bar         ****/
        stbar = new Label("Ready", Label.LEFT);
        add(stbar, BorderLayout.SOUTH);
    }

    /**
     * compress method to remove whitespace and comment lines
     * @param s string extracted from mainText Field
     * @return string without waste memory
     */
    String compress(String s){
        if(s == null || s.length() < 1){
            return null;
        }
        s = s.replaceAll("/*.*?=*/", "");
        s = s.replaceAll("//.*?=\n", "");
        s = s.replaceAll(" ", "");
        s = s.replaceAll("\t", "");
        s = s.replaceAll("\n", "");
        return s;
    }
    
    /**
     * to cut out selected string
     * @param s string extracted from mainText area
     * @param start start of selected text
     * @param end end of selected text
     * @return string with removed selected text
     */
    String remove(String s,int start,int end){
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0, start));
        sb.append(s.substring(end,s.length()));
        return sb.toString();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CSSCompressor c;
        c = new CSSCompressor();
    }

    /**
     * ActionListener implemented method
     * perform on actions of menu items and buttons
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New":
                break;
            case "Open":
                break;
            case "Save":
                break;
            case "Exit":
                break;
            case "Cut":
                cp = mainTxt.getSelectedText();
                int start = mainTxt.getSelectionStart();
                int end = mainTxt.getSelectionEnd();
                String temp1 = remove(mainTxt.getText(),start,end);
                mainTxt.setText(temp1);
                stbar.setText("Cut:\""+cp);
                break;
            case "Copy":
                cp = mainTxt.getSelectedText();
                stbar.setText("Copied: \"" + cp + "\"");
                break;
            case "Paste":
                mainTxt.insert(cp, mainTxt.getCaretPosition());
                break;
            case "Select All":
                mainTxt.select(0, mainTxt.getText().length());
                stbar.setText("Selected All");
                break;
            case "Compress":
                String temp2 = compress(mainTxt.getText());
                comTxt.setText(temp2);
                stbar.setText("Compressed");
                break;
            case "Clear":
                mainTxt.setText("");
                comTxt.setText("");
                stbar.setText("Ready");
                break;
            default:
            //nothing
        }
    }
}
