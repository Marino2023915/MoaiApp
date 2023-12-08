package group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// GroupDao.java
public class GroupDao {
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


    public int createGroup(String groupName, String description, String ownerNo) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // オートコミットモード無効化
            con.setAutoCommit(false);

            // グループレコードを登録するSQL文作成
            String sql = "INSERT INTO Groups (group_name, description, owner_id, creation_date) VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // PreparedStatementの入力パラメータをセット
            stmt.setString(1, groupName);
            stmt.setString(2, description);
            stmt.setString(3, ownerNo);
            stmt.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));

            // SQL文実行
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating group failed, no rows affected.");
            }

            // 自動生成されたキー（グループID）を取得
            rs = stmt.getGeneratedKeys();
            int groupId = 0;
            if (rs.next()) {
                groupId = rs.getInt(1);
            }

            // コミット（確定）
            con.commit();

            // 採番されたグループIDを返す
            return groupId;
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
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // コネクションのオートコミットを元に戻すなどの後処理
        }
    }



}