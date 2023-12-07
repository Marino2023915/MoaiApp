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
<%@ page import="customer.Customer" %>
<%--useBean--%>
<jsp:useBean id="cust" scope="session" type="customer.Customer" />

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
        <h1>さくらマーケット</h1>
        <span class="site-subtitle">MoaiApp</span>
      </div>
      <h2>ホーム</h2>
      <div class="logout"><a href="/SAKURA_ans/system/LogoutServlet">ログアウト</a></div>
    </div>
    <table class="table-menu">
      <tr>
        <th class="menu-selected">
          ホーム
        </th>
        <th>
          <a href="/SAKURA_ans/goods/GoodsSearchServlet">商品一覧</a>
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
      日々の業務お疲れ様です，
      <span class="custmer-name">
        <jsp:getProperty name="cust" property="nameLast" />
        <jsp:getProperty name="cust" property="nameFirst" />
      </span>
      さん。
      <p>
        新しい模合を登録するか、すでにある模合を選択してください
      </p>
    </div>

     <a href="new_registration.jsp">新しい模合を登録</a>

  </main>
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
