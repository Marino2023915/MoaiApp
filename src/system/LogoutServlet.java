/**
 * Copyright nus Academy Co., Ltd. 2008, 2020. All rights reserved.
 *
 * This software is the proprietary information of nus Academy Co., Ltd.
 * Use is subject to license terms.
 */
package system;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 顧客のログアウトを行うServletです。
 * @author nus Academy Co., Ltd
 */
@WebServlet({ "/system/LogoutServlet" })
public class LogoutServlet extends HttpServlet {
    /**
     * HttpServlet#doPost()のオーバーライドです。
     * <br><br>
     *【Information】<br>
     * 機能２
     * <br><br>【Note】<br>
     * doGet()を呼び出します。
     *
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        this.doGet(req, res);
    }

    /**
     * HttpServlet#doGet()のオーバーライドです。
     * <br><br>
     *【Information】<br>
     * 機能２
     * <br><br>【Note】<br>
     * １．以下のルールで，ユーザがログイン済みであるかチェックします。<br>
     * 　　・セッションは存在するか<br>
     * 　　・セッションに"cust"と言う名前の，Customerオブジェクトが存在するか<br>
     * 　　　　→ログインしていない場合「ログインを行ってください。」エラー<br>
     * ２．セッションを破棄し，login.htmlにフォワードします。<br>
     *
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // 遷移先のパス
        String next = null;
        // エラーメッセージ
        String errmsg = null;
        // セッションの取得
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("cust") == null) {
            // ログインしていない場合にエラー画面に遷移
            errmsg = "ログインを行ってください。";
            next = "/system/error.jsp";
            req.setAttribute("errmsg", errmsg);
            req.getRequestDispatcher(next).forward(req, res);
            return;
        }
        // セッションの破棄
        session.invalidate();
        // 遷移先を指定
        next = "/system/login.html";
        // 画面遷移
        req.getRequestDispatcher(next).forward(req, res);
    }
}
