package group;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customer.Customer;

/**
 * 自身の加入している模合グループをリストとして表示するためのサーブレットです
 */
@WebServlet("/group/GroupListServlet")
public class GroupListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

//		  req.setCharacterEncoding("UTF-8");
//		  String next = null;
//	        // エラーメッセージ
//	        String errmsg = null;
//		   // フォームからの入力を取得
//	        String groupName = req.getParameter("group_name");
//	        String description = req.getParameter("description");
//	        System.out.println("groupName"+groupName);
//	        System.out.println("description"+description);
//
//
//	        // セッションからユーザー情報を取得
//	        HttpSession session = req.getSession();
//	        Customer cust = (Customer) session.getAttribute("cust");
//
//	        // DAOを使用してグループをデータベースに追加
//	        GroupDao groupDao = new GroupDao(); // GroupDaoのインスタンス化が必要です
//	        try {
//	            groupDao.connect(); // データベース接続
//	            int groupId = groupDao.createGroup(groupName, description, cust.getNo());
//	            // グループ作成成功の処理...
//	           System.out.println("groupId取得まできた"+groupId);
//
//	           // セッションに情報追加
//               session.setAttribute("cust", cust);
//               System.out.println("cust.getNo()"+cust.getNo());
//
//               // 遷移先を指定
//               next = "/group/add_customer.html";
//	        } catch (Exception e) {
//	        	// 遷移先を指定しエラーメッセージを詰める
//                next = "/system/error.jsp";
//                req.setAttribute("errmsg", e.getMessage());
//                // ログにトレースを出力
//                e.printStackTrace();
//	        } finally {
//	            try {
//					groupDao.close();
//				} catch (SQLException e) {
//					 // 例外に対しては何も処理を行わない
//					e.printStackTrace();
//				} // データベース接続解除
//	        }
//	        // 画面遷移
//	        req.getRequestDispatcher(next).forward(req, res);
	    }




	  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		    req.setCharacterEncoding("UTF-8");
		    String next = "/group/grouplist.jsp"; // デフォルトの遷移先

		    HttpSession session = req.getSession();
		    Customer cust = (Customer) session.getAttribute("cust");
		    if (cust == null) {
		        // ユーザーがログインしていない場合の処理（例：ログインページへリダイレクト）
		        next = "/system/login.jsp";
		        req.setAttribute("errmsg", "ログインが必要です。");
		    } else {
		        GroupDao dao = new GroupDao();
		        try {
		            dao.connect();
		            System.out.println("cust.getNo()="+cust.getNo());
		            // 自分が所属しているグループの情報を取得してリストに格納
		            List<Integer> groupIds = dao.getGroupIdsByUserId(cust.getNo());

		            System.out.println("groupIds.size()="+groupIds.size());

		            List<Group> groups = new ArrayList<>();


		            for (Integer groupId : groupIds) {
		                Group group = dao.getGroupDetailsById(groupId); // GroupIDに基づいてグループ情報を取得
		                List<String> memberNames = dao.getMemberNamesByGroupId(groupId); // グループのメンバー名を取得
		                group.setMemberNames(memberNames); // グループオブジェクトにメンバー名をセット

		                groups.add(group);
		            }

		            req.setAttribute("groups", groups);
		        } catch (SQLException | ClassNotFoundException e) {
		            next = "/system/error.jsp";
		            req.setAttribute("errmsg", e.getMessage());
		            e.printStackTrace();
		        } finally {
		            try {
		                if (dao != null) dao.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		    req.getRequestDispatcher(next).forward(req, res);
		}



}
