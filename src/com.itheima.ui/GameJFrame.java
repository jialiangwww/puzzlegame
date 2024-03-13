package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    // 创建一个二维数组
    // 用来管理数据
    // 加载图片的时候，会根据二维数组中的数据进行加载
    int[][] indexOfPictureTwo = new int[4][4];

    // 记录空白方块在二维数组中的位置
    int x = 0; int y = 0;

    // 定义一个变量，记录当前展示图片的路径
    String path = "image\\animal\\animal3\\";

    // 定义变量用来统计步数
    int step = 0;

    // 定义一个二维数组，存储正确的数字
    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    // 创建选项下面的条目对象
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");


    // 游戏主界面
    public GameJFrame() {
        initJFrame();

        // 初始化菜单
        initJMenuBar();

        // 初始化数据（打乱）
        initData();

        // 初始化图片（根据打乱后的结果去加载图片）
        initImage();

        // 让界面显示出来
        this.setVisible(true);
    }

    private void initData() {
        // 定义一个一维数组保存图片的序号
        int[] indexOfPictureOne = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        // 打乱一维数组顺序
        Random r = new Random();
        for (int i = 0; i < 16; i++) {
            int iRandom = r.nextInt(indexOfPictureOne.length);

            int temp = indexOfPictureOne[i];
            indexOfPictureOne[i] = indexOfPictureOne[iRandom];
            indexOfPictureOne[iRandom] = temp;
        }

        // 一维数组变二维数组
        int index = 0; // 用于遍历一维数组
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                indexOfPictureTwo[i][j] = indexOfPictureOne[index++];
                if (indexOfPictureTwo[i][j] == 0) { //记录空白方块的位置
                    x = i; y = j;
                }
            }
        }

//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                System.out.print(indexOfPictureTwo[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    private void initImage() {
        // 清空原本已经出现的所有图片
        this.getContentPane().removeAll();

        if (victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        // 先加载的图片在上面
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = indexOfPictureTwo[i][j];

                // 创建一个 JLabel 对象，用来管理 ImageIcon 对象
                JLabel jLabel = new JLabel(new ImageIcon(path + num + ".jpg"));

                // 指定图片位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);

                // 给图片设置边框
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));

                // 把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }
        }

        // 后加载的图片在下面
        // 创建一个 JLabel 对象，用来管理 ImageIcon 对象
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));

        // 指定图片位置
        background.setBounds(40, 40, 508, 560);

        // 把管理容器添加到界面中
        this.getContentPane().add(background);

        // 刷新一下界面
        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        // 创建整个的菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        // 创建菜单上面的两个选项对象（功能，关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        // 创建更换图片
        JMenu changeImage = new JMenu("更换图片");

        // 将每个选项的条目添加到对应的选项中去
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);

        functionJMenu.add(changeImage);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        // 给条目绑定事件
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        // 将菜单里面的两个选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        // 给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        // 设置界面的宽高
        this.setSize(603, 680);

        // 设置界面的标题
        this.setTitle("拼图单机版 v1.0");

        // 设置界面置顶
        this.setAlwaysOnTop(true);

        // 设置居中
        this.setLocationRelativeTo(null);

        // 设置关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 取消默认的居中位置，只有取消了才会按照 XY 轴的形式添加组件
        this.setLayout(null);

        // 给整个界面增加键盘键停事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 按下不松时会调用这个方法
    @Override
    public void keyPressed(KeyEvent e) {
        // 判断游戏是否生理，如果胜利，此方法需要直接结束，不能在执行下面的移动代码了
        if (victory()) return;

        int code = e.getKeyCode();
        if (code == 65) {
            // 把界面中所有图片删除
            this.getContentPane().removeAll();

            // 加载一张完整的图片
            JLabel all= new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83,  134, 420, 420);
            this.getContentPane().add(all);

            // 加载背景图片
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);

            // 刷新界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 判断游戏是否生理，如果胜利，此方法需要直接结束，不能在执行下面的移动代码了
        if (victory()) return;

        // 对上下左右进行判断
        // 左：37，上：38，右：39，下：40
        int code = e.getKeyCode();
        if (code == 37) {
            if (y == 3) return;
            System.out.println("向左移动");
            indexOfPictureTwo[x][y] = indexOfPictureTwo[x][y+1];
            indexOfPictureTwo[x][y+1] = 0;
            y++;
            step++; //计步器
            //调用方法按照最新的数字加载图片
            initImage();
        } else if (code == 38) {
            if (x == 3) return;
            System.out.println("向上移动");
            indexOfPictureTwo[x][y] = indexOfPictureTwo[x+1][y];
            indexOfPictureTwo[x+1][y] = 0;
            x++;
            step++; //计步器
            //调用方法按照最新的数字加载图片
            initImage();
        } else if (code == 39) {
            if (y == 0) return;
            System.out.println("向右移动");
            indexOfPictureTwo[x][y] = indexOfPictureTwo[x][y-1];
            indexOfPictureTwo[x][y-1] = 0;
            y--;
            step++; //计步器
            //调用方法按照最新的数字加载图片
            initImage();
        } else if (code == 40) {
            if (x == 0) return;
            System.out.println("向下移动");
            indexOfPictureTwo[x][y] = indexOfPictureTwo[x-1][y];
            indexOfPictureTwo[x-1][y] = 0;
            x--;
            step++; //计步器
            //调用方法按照最新的数字加载图片
            initImage();
        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            indexOfPictureTwo = new int[][] {
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
    }

    // 判断 indexOfPictureTwo 数组中的数据是否跟 win 数组中相同
    // 如果全部相同，返回 true， 否则返回 false
    public boolean victory() {
        for (int i = 0; i < indexOfPictureTwo.length; i++) {
            for (int j = 0; j < indexOfPictureTwo[i].length; j++) {
                if (indexOfPictureTwo[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取当前被点击的条目对象
        Object obj = e.getSource();
        if (obj == girl){
            // 随机选择图片
            // 修改PATH变量记录的值
            // 写一些重开一把的逻辑
        } else if (obj == animal) {
            // 随机选择图片
            // 修改PATH变量记录的值
            // 写一些重开一把的逻辑
        } else if (obj == sport) {
            // 随机选择图片
            // 修改PATH变量记录的值
            // 写一些重开一把的逻辑
        } else if (obj == replayItem) {
            System.out.println("重新游戏");
            // 再次打乱二维数组
            initData();
            // 计步器清零
            step = 0;
            // 重新加载图片
            initImage();
        } else if (obj == reLoginItem) {
            System.out.println("重新登陆");
            // 关闭当前的游戏界面
            this.setVisible(false);
            // 打开登录界面
            new LoginJFrame();
        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            // 直接关闭虚拟机即可
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("公众号");
            // 创建一个弹框对象
            JDialog jDialog = new JDialog();
            jDialog.setSize(344, 344);

            // 创建一个管理图片的容器对象 JLabel
            JLabel jLabel = new JLabel(new ImageIcon("image\\about.png"));
            jLabel.setBounds(0, 0, 258, 258);
            // 把图片添加到弹框当中
            jDialog.getContentPane().add(jLabel);

            // 让弹框置顶,居中
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);

            // 弹框不关闭则无法操作下面的界面
            jDialog.setModal(true);

            // 让弹框显示出来
            jDialog.setVisible(true);
        }
    }
}
