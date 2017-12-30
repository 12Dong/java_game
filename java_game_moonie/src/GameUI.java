import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*
    游戏界面类

 */
public class GameUI extends JFrame{
    private static GameUI UI;
    private java.awt.Graphics g;
//java.awt.Graphics是一个用来绘制2D图像必须导入的java包，提供对图形图像的像素，颜色的绘制。
    private MyListener ml;
    public JLabel text_field;
    public JProgressBar pBar;
    private String command;
    public java.util.ArrayList<MyThread> list = new java.util.ArrayList<MyThread>();
    private JToggleButton button;
    //JButton提供了一个按钮的基本实现，但是由此按钮每次单击之后都会恢复自动弹起。
    // 如果在希望单击一个按钮后不再自动弹起，而是在第二次单击由此按钮时弹起，则就必须使用JToggleButton类。
    public static void main(String args[]){
        UI = new GameUI();
        UI.initUI();
    }

    /*
    初始化 窗体
     */
    public void initUI(){
        //设置标题
        this.setTitle("是高手就坚持100s");
        this.setSize(610, 635);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new java.awt.FlowLayout());
        this.getContentPane().setBackground(java.awt.Color.black);
        //创建文本标签对象
        text_field = new javax.swing.JLabel();
        javax.swing.JLabel label = new javax.swing.JLabel("时 间 : ");
        //设置文本标签前景颜色
        label.setForeground(java.awt.Color.red);
        text_field.setForeground(java.awt.Color.red);

        //创建进度条对象
        //JProgressBar是一个简单的组件，它一般是一种颜色部分或完全填充的矩形。缺省情况下，进度条配备了一个凹陷的边框，并水平放置。
        //　　进度条还可以选择显示一个字符串，这个字符串在进度条矩形的中央位置上显示。这个字符串缺省时为耗时任务已完成的百分比。
        pBar = new javax.swing.JProgressBar(0,330);
        button = new javax.swing.JToggleButton();
        button.setMargin(new Insets(0,0,0,0));
        button.setIcon(new javax.swing.ImageIcon("F:\\yanjiu\\java\\java_game_moonie\\button.jpg"));
        button.setActionCommand("暂停");
        //通过匿名内部类来创建动作监听器
        java.awt.event.ActionListener button_listener = new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                String command = e.getActionCommand();
                if(command.equals("暂停")){
                    button.setMargin(new Insets(0,0,0,0));
                    button.setIcon(new javax.swing.ImageIcon());
                    button.setActionCommand("继续");
                    for(int i=0;i<list.size();i++){
                        list.get(i).PauseThread();
                    }
                }
                else if(command.equals("继续")){
                    button.setMargin(new Insets(0,0,0,0));
                    button.setIcon(new javax.swing.ImageIcon("F:\\yanjiu\\java\\java_game_moonie\\button.jpg"));
                    button.setActionCommand("暂停");
                    for(int i=0;i<list.size();i++){
                        list.get(i).ContinueThread();
                    }
                }
            }
        };

        button.addActionListener(button_listener);
        this.add(button);
        this.add(label);
        this.add(pBar);
        this.add(text_field);
        //菜单条
        javax.swing.JMenuBar bar = createMenuBar();
        this.setJMenuBar(bar);
        this.setVisible(true);
    }

    //创建菜单条的方法
    public javax.swing.JMenuBar createMenuBar(){
        //创建菜单条对象
        javax.swing.JMenuBar bar = new javax.swing.JMenuBar();
        //创建菜单对象
        javax.swing.JMenu menu_menu = new javax.swing.JMenu("菜单");
        javax.swing.JMenu difficulty_menu = new javax.swing.JMenu("难度");
        javax.swing.JMenu help_menu = new javax.swing.JMenu("帮助");
        //创建菜单选项对象
        javax.swing.JMenuItem star_item = new javax.swing.JMenuItem("开始");
        javax.swing.JMenuItem exit_item = new javax.swing.JMenuItem("退出");
        javax.swing.JMenuItem help_item = new javax.swing.JMenuItem("游戏说明");
        javax.swing.JMenuItem about_item = new javax.swing.JMenuItem("关于");
        // 创建单选选项
        javax.swing.JRadioButtonMenuItem easy_item = new javax.swing.JRadioButtonMenuItem("简单");
        javax.swing.JRadioButtonMenuItem middle_item = new javax.swing.JRadioButtonMenuItem("中等");
        javax.swing.JRadioButtonMenuItem hard_item = new javax.swing.JRadioButtonMenuItem("困难");
        //创建一个按钮
        javax.swing.ButtonGroup group = new javax.swing.ButtonGroup();
        group.add(easy_item);
        group.add(middle_item);
        group.add(hard_item);
        //将单选按钮添加到菜单中
        difficulty_menu.add(easy_item);
        difficulty_menu.add(middle_item);
        difficulty_menu.add(hard_item);
        ActionListener listener = new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e){
                command = e.getActionCommand();
                //如果选择开始,则创建线程对象
                if(command.equals("开始")&&list.size()==0){
                    createBall(20,1);
                }
                if(command.equals("退出")){
                    System.exit(0);
                }
                //如果选择简单按钮
                if(command.equals("简单") && list.size()==0){
                    createBall(20,1);
                }
                if(command.equals("中等") && list.size()==0){
                    createBall(50,2);
                }
                if(command.equals("困难") && list.size()==0){
                    createBall(40,2);
                }
                if(command.equals("游戏说明")){
                    javax.swing.JOptionPane.showMessageDialog(null,"移动鼠标,用挡板接球,如果接不住"+
                    ",就算输了....\n游戏可以选择难度,包括简单,中等,困难");
                }
                if(command.equals("关于")){
                    javax.swing.JOptionPane.showMessageDialog(null,"这是参考自沈冠军的java游戏\n"+
                            "这是网址http://champion-netjava-163-com.iteye.com/blog/729419");
                }
            }
        };
        star_item.addActionListener(listener);
        exit_item.addActionListener(listener);
        easy_item.addActionListener(listener);
        middle_item.addActionListener(listener);
        hard_item.addActionListener(listener);
        help_item.addActionListener(listener);
        menu_menu.add(star_item);
        menu_menu.add(exit_item);
        help_menu.add(help_item);
        help_menu.add(about_item);
        //将菜单对象添加到菜单条上
        bar.add(menu_menu);
        bar.add(difficulty_menu);
        bar.add(help_menu);
        //返回菜单条对象
        return bar;
    }
    //创建线程对象的方法
    public void createBall(int speed,int num){
        java.util.Random random = new java.util.Random();
        if(ml==null){
            g = UI.getGraphics();
            ml = new MyListener(g);
            UI.addMouseListener(ml);
            UI.addMouseMotionListener(ml);
        }
        for(int i=0;i<num;i++){
            int x = random.nextInt(600)+10;
            int y = random.nextInt(300)+100;
            MyThread th = new MyThread(g,ml,UI,x,y,speed);
            list.add(th);
            th.start();
        }
    }
    public String getCommand(){
        return command;
    }
}

