package scoremanager.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Menu {
    public static void main(String[] args) {
        // フレームの作成
        JFrame frame = new JFrame("得点管理システム");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // メインパネル（BorderLayout）
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 上部パネル（タイトルとユーザー情報）
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("得点管理システム");
        titleLabel.setFont(new Font("Meiryo", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
        topPanel.add(titleLabel, BorderLayout.WEST);

        JLabel userLabel = new JLabel("大阪 太郎様　|　ログアウト", SwingConstants.RIGHT);
        userLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(userLabel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // 左側メニュー
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(new Dimension(150, 0));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));
        menuPanel.add(new JLabel("メニュー"));
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(new JButton("学生管理"));
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(new JLabel("成績管理"));
        menuPanel.add(new JButton("成績登録"));
        menuPanel.add(new JButton("成績参照"));
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(new JButton("科目管理"));

        mainPanel.add(menuPanel, BorderLayout.WEST);

        // 中央メニューパネル
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 3, 20, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));

        // 各メニューボタン（色付きパネル）
        centerPanel.add(createMenuPanel("② 学生管理", Color.PINK));
        centerPanel.add(createMenuPanel("③ 成績管理\n④ 成績登録\n⑤ 成績参照", Color.GREEN));
        centerPanel.add(createMenuPanel("⑥ 科目管理", Color.CYAN));

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // フッター
        JLabel footer = new JLabel("© 2023 TIC　大阪学院", SwingConstants.CENTER);
        footer.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(footer, BorderLayout.SOUTH);

        // フレームにメインパネルを追加して表示
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    private static JPanel createMenuPanel(String text, Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setLayout(new BorderLayout());

        JTextArea label = new JTextArea(text);
        label.setFont(new Font("Meiryo", Font.PLAIN, 16));
        label.setEditable(false);
        label.setOpaque(false);
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }
}
