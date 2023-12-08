package group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// GroupDao.java
public class GroupParticipantDao {
	/**
     * 接続情報 ドライバクラス名
     * <br><br>
     * 【Information】<br>
     * 機能１
     * <br>
     */
	private static final String DRIVER = "org.postgresql.Driver";

    /**
     * 接続情報 接続先URL
     * <br><br>
     * 【Information】<br>
     * 機能１
     * <br>
     */
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    /**
     * 接続情報 ユーザ名
     * <br><br>
     * 【Information】<br>
     * 機能１
     * <br>
     */
    private static final String USER = "postgres";

    /**
     * 接続情報 パスワード
     * <br><br>
     * 【Information】<br>
     * 機能１
     * <br>
     */
    private static final String PASSWD = "marino";


    /**
     * コネクション
     * <br><br>
     * 【Information】<br>
     * 機能１
     * <br>
     */
    private Connection con = null;

    /**
     * JDBC Driverを登録し，データベースに接続します。
     * <br><br>
     * 【Information】<br>
     * 機能１
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */



    /**
     * JDBC Driverを登録し，データベースに接続します。
     * <br><br>
     * 【Information】<br>
     * 機能１
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void connect() throws ClassNotFoundException, SQLException {
        try {
            // JDBC Driverの登録
            Class.forName(DRIVER);
            // DBへ接続
            con = DriverManager.getConnection(URL, USER, PASSWD);
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        }
    }


    /**
     * データベースから切断します。
     * <br><br>
     * 【Information】<br>
     * 機能１
     * <br><br>【Error】<br>
     * 発生してcatchした例外は，そのまま呼び出しもとにthrowします。
     *
     * @throws SQLException
     */
    public void close() throws SQLException {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }


    public void addParticipant(String groupId, String customerNo) throws SQLException {
        PreparedStatement stmt1 = null;
       System.out.println("groupId="+groupId);
       System.out.println("customerNo="+customerNo);
        try {
            // オートコミットモード無効化
            con.setAutoCommit(false);

            // GroupParticipantsテーブルにレコードを追加するSQL文作成
            String sql = "INSERT INTO GroupParticipants (group_id, customer_no) VALUES (?, ?)";
            stmt1 = con.prepareStatement(sql);


            // PreparedStatementの入力パラメータをセット
            stmt1.setInt(1, Integer.parseInt(groupId));
            stmt1.setString(2, customerNo);

            // SQL文実行
            int affectedRows = stmt1.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding participant failed, no rows affected.");
            }

            // コミット（確定）
            con.commit();


        } catch (SQLException e) {
            // ロールバック
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException se) {
                    // ロールバック失敗時のエラー処理
                    se.printStackTrace();
                }
            }
            // 発生したSQL例外を再スロー
            throw e;
        } finally {
            // リソースの解放
            if (stmt1 != null) {
                try {
                	stmt1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // コネクションのオートコミットを元に戻すなどの後処理
        }
    }




}
