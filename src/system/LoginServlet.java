/**
 * Copyright nus Academy Co., Ltd. 2008, 2020. All rights reserved.
 *
 * This software is the proprietary information of nus Academy Co., Ltd.
 * Use is subject to license terms.
 */
package system;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customer.Customer;
import customer.CustomerDao;

/**
 * 顧客のログイン認証を行うServletです。
 * @author nus Academy Co., Ltd
 */
@WebServlet({ "/MoaiApp/src/system/LoginServlet.java" })
public class LoginServlet extends HttpServlet {
    /**
     * HttpServlet#doPost()のオーバーライドです。
     * <br><br>
     * 【Information】<br>
     * 機能２
     * <br><br>【Note】<br>
     * １．入力チェックを行い，エラーがあった場合はエラー画面に遷移します。<br>
     * 　(入力チェックの詳細に関してはモックアップを参照してください)<br>
     * ２．CustomerDAOによってデータベースに接続し，<br>
     * 　　顧客番号とパスワードからログイン認証を行います。<br>
     * ３．ログイン認証後，取得した顧客情報を，名前"cust"でセッションに格納します。<br>
     * 　　　→顧客情報が取得できなかった場合，<br>
     * 　　　　「顧客番号またはパスワードに誤りがあります。再度ログインしてください。」エラー<br>
     * ４．home.jspにフォワードします。<br>
     * <br><br>【Error】<br>
     * catchした各例外に対しては，以下の処理を行います。<br>
     * 　・フォワード先をerror.jsp(エラー画面)に設定します。<br>
     * 　・エラー画面に表示するerrmsgに例外メッセージを設定します。<br>
     * 　・スタックトレースをコンソール画面に表示します。<br>
     * ただし，データベース切断時の例外は，特に何もしません。<br>
     *
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // 使用するDAO
        CustomerDao dao = null;
        // 遷移先のパス
        String next = null;
        // エラーメッセージ
        String errmsg = null;
        try {
            // リクエストのエンコーディング
            req.setCharacterEncoding("UTF-8");
            // パラメタの取得
            String custNo = req.getParameter("no");
            String password = req.getParameter("password");
            // 入力チェック
            if (custNo == null || custNo.trim().length() == 0) {
                // 顧客番号は必須
                errmsg = "顧客番号は必須です。";
            } else if (password == null || password.trim().length() == 0) {
                // パスワードは必須
                errmsg = "パスワードは必須です。";
            }
            // 入力チェックにてエラーがあった場合
            if (errmsg != null) {
                // 遷移先を指定しエラーメッセージを詰める
                next = "/system/error.jsp";
                req.setAttribute("errmsg", errmsg);
            } else {
                // CustomerDao生成
                dao = new CustomerDao();
                // データベースに接続
                dao.connect();
                // ログイン認証
                Customer cust = dao.certify(custNo, password);
                if (cust != null) {
                    // 認証OK
                    // セッションの生成／取得
                    HttpSession session = req.getSession(true);
                    // セッションに情報追加
                    session.setAttribute("cust", cust);
                    // 遷移先を指定
                    next = "/system/home.jsp";
                } else {
                    // 認証NG
                    errmsg = "顧客番号またはパスワードに誤りがあります。再度ログインしてください。";
                    // JSPに送るデータの設定
                    req.setAttribute("errmsg", errmsg);
                    next = "/system/error.jsp";
                }
            }
        } catch (ClassNotFoundException e) {
            // 遷移先を指定しエラーメッセージを詰める
            next = "/system/error.jsp";
            req.setAttribute("errmsg", e.getMessage());
            // ログにトレースを出力
            e.printStackTrace();
        } catch (SQLException e) {
            // 遷移先を指定しエラーメッセージを詰める
            next = "/system/error.jsp";
            req.setAttribute("errmsg", e.getMessage());
            // ログにトレースを出力
            e.printStackTrace();
        } finally {
            if (dao != null) {
                try {
                    dao.close();
                    // 例外に対しては何も処理を行わない
                } catch (SQLException e) {
                }
            }
        }
        // 画面遷移
        req.getRequestDispatcher(next).forward(req, res);
    }

    /**
     * HttpServlet#doGet()のオーバーライドです。
     * <br><br>
     * 【Information】<br>
     * 機能２
     * <br><br>【Note】<br>
     * doPost()を呼び出します。<br>
     *
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        this.doPost(req, res);
    }
}
