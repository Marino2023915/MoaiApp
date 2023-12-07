/**
 * Copyright nus Academy Co., Ltd. 2008, 2020. All rights reserved.
 *
 * This software is the proprietary information of nus Academy Co., Ltd.
 * Use is subject to license terms.
 */
package customer;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 顧客情報を登録するServletです。
 * @author nus Academy Co., Ltd
 */
@WebServlet({ "/customer/CustomerRegistServlet" })
public class CustomerRegistServlet extends HttpServlet {
    /**
     * HttpServlet#doPost()のオーバーライドです。
     * <br><br>
     * 【Information】<br>
     * 機能１
     * <br><br>【Note】<br>
     * １．入力チェックを行い，エラーがあった場合はエラー画面に遷移します。<br>
     * 　(入力チェックの詳細に関してはモックアップを参照してください)<br>
     * ２．入力値をCustomerに設定します。<br>
     * ３．Customerを引数として，CustomerDaoに顧客情報の登録を指示します。<br>
     * ４．登録の結果，返された顧客番号を名前"no"でリクエストに格納します。<br>
     * ５．customer_accept.jspにフォワードします。<br>
     * <br>【Example】
     * <pre>{@literal
     * //文字数チェック
     * if(nameFirst.trim().length() >= 6
     *         || nameLast.trim().length() >= 6) {
     *     //姓・名は5文字以内
     *     errmsg = "姓・名は5文字以下です。";
     * }
     * }</pre>
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
        // リクエストのエンコーディング
        req.setCharacterEncoding("UTF-8");
        // パラメタの取得
        String nameLast = req.getParameter("nameLast");
        String nameFirst = req.getParameter("nameFirst");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        String postalNo1 = req.getParameter("postalNo1");
        String postalNo2 = req.getParameter("postalNo2");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        // 入力チェック
        if (nameLast == null || nameLast.trim().length() == 0
                || nameFirst == null || nameFirst.trim().length() == 0) {
            // 姓・名は必須
            errmsg = "姓・名は必須です。";
        } else if (nameFirst.trim().length() >= 6
                || nameLast.trim().length() >= 6) {
            // 姓・名は5文字以内
            errmsg = "姓・名は5文字以下です。";
        } else if (password1 == null || password1.trim().length() == 0) {
            // パスワードは必須
            errmsg = "パスワードは必須です。";
        } else if (password1.trim().length() < 4
                || password1.trim().length() >= 11) {
            // パスワードは4文字以上10文字以内
            errmsg = "パスワードは4文字以上10文字以内です。";
        } else if (!password1.equals(password2)) {
            // パスワード整合性チェック
            errmsg = "不正なパスワードです。";
        } else if (postalNo1 == null || postalNo1.trim().length() == 0
                || postalNo2 == null || postalNo2.trim().length() == 0) {
            // 郵便番号は必須
            errmsg = "郵便番号は必須です。";
        } else if (postalNo1.trim().length() != 3
                || postalNo2.trim().length() != 4) {
            // 郵便番号の前半は3桁，後半は4桁
            errmsg = "郵便番号の桁が違います。";
        } else if (address == null || address.trim().length() == 0) {
            // 住所は必須
            errmsg = "住所は必須です。";
        } else if (address.trim().length() >= 26) {
            // 住所は25文字以内
            errmsg = "住所は25文字以内です。";
        } else if (phone == null || phone.trim().length() == 0) {
            // 電話番号は必須
            errmsg = "電話番号は必須です。";
        } else if (phone.trim().length() >= 12) {
            // 電話番号は11桁以下
            errmsg = "電話番号は11桁以内です。";
        } else if (email != null && email.trim().length() >= 21) {
            // e-Mailは20文字以内
            errmsg = "e-Mailは20文字以内です。";
        }
        // 入力チェックにてエラーがあった場合
        if (errmsg != null) {
            // 遷移先を指定しエラーメッセージを詰める
            next = "/system/error.jsp";
            req.setAttribute("errmsg", errmsg);
        } else {
            try {
                // Customerを生成しパラメタを設定
                Customer cust = new Customer();
                cust.setNameLast(nameLast);
                cust.setNameFirst(nameFirst);
                cust.setPassword(password1);
                cust.setPostalNo(postalNo1 + postalNo2);
                cust.setAddress(address);
                cust.setPhone(phone);
                cust.setEmail(email);
                // CustomerDao生成
                dao = new CustomerDao();
                // データベースに接続
                dao.connect();
                // 顧客データを登録
                String no = dao.create(cust);
                // 採番された顧客番号はリクエストに保存
                req.setAttribute("no", no);
                // 遷移先を指定
                next = "/customer/customer_accept.jsp";
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
        }
        // 画面遷移
        req.getRequestDispatcher(next).forward(req, res);
    }

    /**
     * HttpServlet#doGet()のオーバーライドです。
     * <br><br>
     * 【Information】<br>
     * 機能１
     * <br><br>【Note】<br>
     * doPost()を呼び出します。
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
