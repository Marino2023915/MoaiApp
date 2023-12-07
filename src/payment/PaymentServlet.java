/**
 * Copyright nus Academy Co., Ltd. 2008, 2020. All rights reserved.
 *
 * This software is the proprietary information of nus Academy Co., Ltd.
 * Use is subject to license terms.
 */
package payment;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.CustomerDao;

/**
 * 顧客情報を登録するServletです。
 * @author nus Academy Co., Ltd
 */
@WebServlet({ "/payment/PaymentServlet" })
public class PaymentServlet extends HttpServlet {
    /**
     * HttpServlet#doPost()のオーバーライドです。
     * <br><br>
     * 【Information】<br>
     * 機能１

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
        String customerName = req.getParameter("customer_name"); // 顧客名を取得
        int amount = Integer.parseInt(req.getParameter("amount")); // 金額を整数として取得
        String dateString = req.getParameter("date");
        LocalDate startDate = LocalDate.parse(dateString); // 文字列からLocalDateに変換
        String note = req.getParameter("note"); // 備考を取得

        //出力して確認
        System.out.println(customerName);
        System.out.println(amount);
        System.out.println(startDate);
        System.out.println(note);



     // 動的に生成されたフィールドの値を取得
        int numberOfPeople = Integer.parseInt(req.getParameter("number_of_people"));
        List<String> names = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            String name = req.getParameter("person_name_" + i);
         // 名前をコンソールに出力
            System.out.println(name);
            if (name != null && !name.isEmpty()) {
                names.add(name);
            }
        }







//
//
//        // 入力チェック
//        if (nameLast == null || nameLast.trim().length() == 0
//                || nameFirst == null || nameFirst.trim().length() == 0) {
//            // 姓・名は必須
//            errmsg = "姓・名は必須です。";
//        } else if (nameFirst.trim().length() >= 6
//                || nameLast.trim().length() >= 6) {
//            // 姓・名は5文字以内
//            errmsg = "姓・名は5文字以下です。";
//        } else if (password1 == null || password1.trim().length() == 0) {
//            // パスワードは必須
//            errmsg = "パスワードは必須です。";
//        } else if (password1.trim().length() < 4
//                || password1.trim().length() >= 11) {
//            // パスワードは4文字以上10文字以内
//            errmsg = "パスワードは4文字以上10文字以内です。";
//        } else if (!password1.equals(password2)) {
//            // パスワード整合性チェック
//            errmsg = "不正なパスワードです。";
//        } else if (postalNo1 == null || postalNo1.trim().length() == 0
//                || postalNo2 == null || postalNo2.trim().length() == 0) {
//            // 郵便番号は必須
//            errmsg = "郵便番号は必須です。";
//        } else if (postalNo1.trim().length() != 3
//                || postalNo2.trim().length() != 4) {
//            // 郵便番号の前半は3桁，後半は4桁
//            errmsg = "郵便番号の桁が違います。";
//        } else if (address == null || address.trim().length() == 0) {
//            // 住所は必須
//            errmsg = "住所は必須です。";
//        } else if (address.trim().length() >= 26) {
//            // 住所は25文字以内
//            errmsg = "住所は25文字以内です。";
//        } else if (phone == null || phone.trim().length() == 0) {
//            // 電話番号は必須
//            errmsg = "電話番号は必須です。";
//        } else if (phone.trim().length() >= 12) {
//            // 電話番号は11桁以下
//            errmsg = "電話番号は11桁以内です。";
//        } else if (email != null && email.trim().length() >= 21) {
//            // e-Mailは20文字以内
//            errmsg = "e-Mailは20文字以内です。";
//        }
//        // 入力チェックにてエラーがあった場合
//        if (errmsg != null) {
//            // 遷移先を指定しエラーメッセージを詰める
//            next = "/system/error.jsp";
//            req.setAttribute("errmsg", errmsg);
//        } else {
//            try {
//                // Customerを生成しパラメタを設定
//                Customer cust = new Customer();
//                cust.setNameLast(nameLast);
//                cust.setNameFirst(nameFirst);
//                cust.setPassword(password1);
//                cust.setPostalNo(postalNo1 + postalNo2);
//                cust.setAddress(address);
//                cust.setPhone(phone);
//                cust.setEmail(email);
//                // CustomerDao生成
//                dao = new CustomerDao();
//                // データベースに接続
//                dao.connect();
//                // 顧客データを登録
//                String no = dao.create(cust);
//                // 採番された顧客番号はリクエストに保存
//                req.setAttribute("no", no);
//                // 遷移先を指定
//                next = "/customer/customer_accept.jsp";
//            } catch (ClassNotFoundException e) {
//                // 遷移先を指定しエラーメッセージを詰める
//                next = "/system/error.jsp";
//                req.setAttribute("errmsg", e.getMessage());
//                // ログにトレースを出力
//                e.printStackTrace();
//            } catch (SQLException e) {
//                // 遷移先を指定しエラーメッセージを詰める
//                next = "/system/error.jsp";
//                req.setAttribute("errmsg", e.getMessage());
//                // ログにトレースを出力
//                e.printStackTrace();
//            } finally {
//                if (dao != null) {
//                    try {
//                        dao.close();
//                        // 例外に対しては何も処理を行わない
//                    } catch (SQLException e) {
//                    }
//                }
//            }
//        }
//        // 画面遷移
//        req.getRequestDispatcher(next).forward(req, res);
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
