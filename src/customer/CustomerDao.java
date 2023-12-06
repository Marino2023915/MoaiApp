/**
 * Copyright nus Academy Co., Ltd. 2008, 2020. All rights reserved.
 *
 * This software is the proprietary information of nus Academy Co., Ltd.
 * Use is subject to license terms.
 */
package customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.StringUtility;

/**
 * CUSTOMERS(顧客)テーブルにアクセスしデータを永続化・復元します。
 * @author nus Academy Co., Ltd
 */
public class CustomerDao {
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
    private static final String URL = "jdbc:postgresql://localhost:5432/moaiApp";

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
     * 顧客オブジェクトを基に，顧客データをINSERTします。
     * <br><br>
     * 【Information】<br>
     * 機能１
     * <br><br>【Note】<br>
     * １．オートコミットモードを無効にします。<br>
     * ２．次の方法で新たに顧客番号を作成します。顧客番号は規定桁数で0詰します。<br>
     * 　　・postgresqlでは，customer_no_seqシーケンステーブルから顧客番号を取得します。<br>
     * ３．CUSTOMERSテーブルに顧客情報を登録します。<br>
     * ４．トランザクションをコミットし，顧客番号を返します。<br>
     * <br>【Example】
     * <pre>{@literal
     * //postgresqlにおけるシーケンステーブルの使用方法
     * //シーケンステーブルとは連番を生成・管理する仕組みです。
     * //PRIMARY KEY項目に設定するユニークな番号を取得するために使用します。
     * //シーケンス操作関数であるnextval('シーケンステーブル名')をSELECT文に記述することで，
     * //シーケンステーブルが生成する連番を取得できます。
     * SELECT nextval('customer_no_seq') AS customer_no
     * //StringUtilityの利用方法
     * //intの1を10桁，0詰めに変換(1→0000000001)
     * String str = StringUtility.pad(1, 10, '0');
     * }</pre>
     * <br><br>【Error】<br>
     * catchした各例外に対しては，以下の処理を行います。<br>
     * ・トランザクション内で発生した例外は，ロールバックした後，そのまま呼び出しもとにthrowします。<br>
     * ・ロールバックで発生した例外は，特に何もしません。<br>
     * ・リソースの解放で発生した例外は，特に何もしません。
     *
     * @param cust 顧客情報(顧客オブジェクト)です。
     * @return String 採番された顧客番号です。
     * @throws SQLException
     */
    public String create(Customer cust) throws SQLException {
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        try {
            // オートコミットモード無効
            con.setAutoCommit(false);
            // 顧客番号を作成するSQL作成
            String sql1 = "SELECT nextval('customer_no_seq') AS customer_no";
            stmt1 = con.prepareStatement(sql1);
            // SQL実行
            rs = stmt1.executeQuery();
            // 初回登録時は1とする
            int customerNo = 1;
            if (rs.next()) {
                customerNo = rs.getInt("customer_no");
            }
            // 規定桁数で0詰めし顧客番号を作成
            String insCustomerNo = StringUtility.pad(customerNo, 10, '0');

            // 顧客レコードを登録するSQL文作成
            String sql2 = "INSERT INTO customers VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            // PreparedStatementのオブジェクト生成
            stmt2 = con.prepareStatement(sql2);
            // PreparedStatementの入力パラメータをセット
            stmt2.setString(1, insCustomerNo);
            stmt2.setString(2, cust.getNameLast());
            stmt2.setString(3, cust.getNameFirst());
            stmt2.setString(4, cust.getPassword());
            stmt2.setString(5, cust.getPostalNo());
            stmt2.setString(6, cust.getAddress());
            stmt2.setString(7, cust.getPhone());
            stmt2.setString(8, cust.getEmail());
            // SQL文実行
            stmt2.executeUpdate();
            // コミット(確定)
            con.commit();
            // 採番された顧客番号を返す
            return insCustomerNo;
        } catch (SQLException e) {
            try {
                // ロールバック
                con.rollback();
            } catch (SQLException se) {
            }
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt1 != null) {
                    stmt1.close();
                }
                if (stmt2 != null) {
                    stmt2.close();
                }
                // 例外に対しては何も処理を行わない
            } catch (SQLException e) {
            }
        }
    }

    /**
     * ログイン認証を行います。
     * <br><br>
     * 【Information】<br>
     * 機能２
     * <br><br>【Note】<br>
     * １．顧客番号とパスワードから，顧客データを検索します。<br>
     * ２．取得した顧客データをCustomerに設定し返します。<br>
     * 　　なお，該当する顧客データが存在しなかった場合にはnullを返します。<br>
     * <br><br>【Error】<br>
     * 発生してcatchした例外は，そのまま呼び出しもとにthrowします。<br>
     * ただし，リソースの解放で発生した例外は，特に何もしません。
     *
     * @param custNo 顧客番号です。
     * @param custPass 顧客パスワードです。
     * @return Customer ログインした顧客の顧客情報です。
     * @throws SQLException
     */
    public Customer certify(String custNo, String custPass) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // 顧客データを設定するためのCustomer型の変数を宣言
            Customer cust = null;
            // 顧客データを検索するSQL作成
            String sql = "SELECT customer_no,customer_name_last,customer_name_first,customer_password,customer_postal_no,customer_address,customer_phone,customer_email "
                    + " FROM customers "
                    + " WHERE customer_no = ? AND customer_password = ? ";
            stmt = con.prepareStatement(sql);
            // PreparedStatementの入力パラメータをセット
            stmt.setString(1, custNo);
            stmt.setString(2, custPass);
            // SQL実行
            rs = stmt.executeQuery();
            if (rs.next()) {
                // 認証OK
                // Customerオブジェクトを生成
                cust = new Customer();
                // 顧客データを設定
                cust.setNo(rs.getString("customer_no"));
                cust.setNameLast(rs.getString("customer_name_last"));
                cust.setNameFirst(rs.getString("customer_name_first"));
                cust.setPassword(rs.getString("customer_password"));
                cust.setPostalNo(rs.getString("customer_postal_no"));
                cust.setAddress(rs.getString("customer_address"));
                cust.setPhone(rs.getString("customer_phone"));
                cust.setEmail(rs.getString("customer_email"));
            }
            return cust;
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                // 例外に対しては何も処理を行わない
            } catch (SQLException e) {
            }
        }
    }
}