package group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Group作成
     */
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
    /**
     *ユーザーIDを引数として受け取り、そのユーザーが所属するグループの一覧を返すメソッドを作成します。
     */
    public List<Integer> getGroupIdsByUserId(String userId) throws SQLException {
        List<Integer> groupIds = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT group_id FROM GroupParticipants WHERE user_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                groupIds.add(rs.getInt("group_id"));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return groupIds;
    }

<<<<<<< HEAD
//指定されたグループIDに基づいてグループの詳細情報をデータベースから取得し、Group オブジェクトに格納するメソッド
=======
    /**
     *
     */
>>>>>>> master
    public Group getGroupDetailsById(int groupId) throws SQLException {
        Group group = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Groups WHERE group_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, groupId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                group = new Group();
                group.setGroup_No(rs.getInt("group_id"));
                group.setGroup_Name(rs.getString("group_name"));
                group.setDescription(rs.getString("description"));
                group.setOwner_Id(rs.getString("owner_id"));
                group.setCreation_Date(rs.getDate("creation_date"));
                // 他の必要なフィールドも同様にセット
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return group;
    }


    /**
     *GroupParticipants テーブルからユーザーIDを取得し、それに基づいてユーザーの名前を取得しています。
     */
    public List<String> getMemberNamesByGroupId(int groupId) throws SQLException {
        List<String> memberNames = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // ユーザー情報を含むテーブルとGroupParticipantsテーブルを結合するSQLクエリ
            String sql = "SELECT u.name FROM Users u INNER JOIN GroupParticipants gp ON u.user_id = gp.user_id WHERE gp.group_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, groupId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                memberNames.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            // エラーハンドリング
            throw e;
        } finally {
            // リソースの解放
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return memberNames;
    }



//特定のグループIDに所属するメンバーの名前のリストをデータベースから取得するためのメソッド
    public List<String> getMemberNamesByGroupId(int groupId) throws SQLException {
        List<String> memberNames = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            /*クエリの構成
             * SELECT u.name: Users テーブルからユーザーの名前 (name) を選択します。
             * FROM Users u: Users テーブルを u というエイリアス（別名）で参照します。
             * INNER JOIN GroupParticipants gp: GroupParticipants テーブルを gp というエイリアスで結合します。
             * INNER JOIN は両方のテーブルにマッチするレコードのみを結果に含めます。
             * ON u.user_id = gp.user_id: Users テーブルの user_id と
             * GroupParticipants テーブルの user_id が一致するレコードを結合の条件として使用します。
             * WHERE gp.group_id = ?: GroupParticipants テーブルの
             *  group_id が指定されたグループIDと一致するレコードのみをフィルタリングします。
             * */
            String sql = "SELECT u.name FROM Users u INNER JOIN GroupParticipants gp ON u.user_id = gp.user_id WHERE gp.group_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, groupId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                memberNames.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            // エラーハンドリング
            throw e;
        } finally {
            // リソースの解放
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return memberNames;
    }


}
