<!--
**
* Copyright nus Academy Co., Ltd. 2008, 2020. All rights reserved.
*
* This software is the proprietary information of nus Academy Co., Ltd.
* Use is subject to license terms.
**
-->
<%--encoding--%>
<%@ page pageEncoding="UTF-8"
  contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="group.Group" %> <!-- Groupクラスのインポート -->


<%--useBean--%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム【MoaiApp】</title>
<link href="../css/common.css" rel="stylesheet">
</head>
<body>
<!-- ヘッダ -->
  <header>
    <div class="contents-wrap-title">
      <div class="site-title">
        <h1>MoaiApp</h1>
        <span class="site-subtitle">MoaiApp</span>
      </div>
      <h2>ホーム</h2>
      <div class="logout"><a href="/MoaiApp/system/LogoutServlet">ログアウト</a></div>
    </div>
    <table class="table-menu">
      <tr>
        <th class="menu-selected">
          ホーム
        </th>
        <th>
          <a href="/MoaiApp/group/group_creation.html">新規グループ作成</a>
        </th>
        <th>
          <a href="/SAKURA_ans/goods/goods_search.html">商品検索</a>
        </th>
        <th>
          <a href="/SAKURA_ans/order/ViewCartServlet">買い物かご</a>
        </th>
      </tr>
    </table>
  </header>
<!-- メインコンテンツ -->

  <main>
    <div class="message">
      商品の情報を参照する場合は，各商品左のボタンを押してください。
    </div>
    <table class="table-product-info">
      <tr class="tr-product-info-header">
        <th>模合グループ名</th>
        <th>グループの説明</th>
        <th>グループ作成者</th>
        <th>模合グループメンバー</th>
        <th>グループ作成日</th>
      </tr>
<%
List<Group> groups = (List<Group>) request.getAttribute("groups");
for(Group group : groups) {
%>
<tr>
    <td><%= group.getGroup_Name() %></td>
    <td><%= group.getDescription() %></td>
    <td><%= group.getOwner_Id() %></td>
    <td>
        <% for(String memberName : group.getMemberNames()) { %>
            <%= memberName %><br>
        <% } %>
    </td>
    <td><%= group.getCreation_Date()%></td>
</tr>
<%
}
%>
    </table>
  </main>
>>>>>>> master
<!-- フッタ -->
  <footer>
    <hr>
    <p>
      お問い合わせ：仲宗根<br>
      <a href="mailto: ">Mail:marino.nakasone@gmail.com</a>
    </p>
    <p>marino.nakasone 2023, 1205.</p>
  </footer>
</body>
</html>