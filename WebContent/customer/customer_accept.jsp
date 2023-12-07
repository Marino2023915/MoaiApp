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
<%--useBean--%>
<jsp:useBean id="no" scope="request" type="java.lang.String" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録完了【MoaiApp】</title>
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
      <h2>新規登録完了</h2>
    </div>
    <hr class="hr-header">
  </header>
<!-- メインコンテンツ -->
  <main>
    <div class="message">
      <p>
        ご登録まことにありがとうございます。<br>
        下記の顧客番号で<a href="../system/login.html">こちら</a>からログインしてください。
      </p>
      <table class="table-customer-number">
        <tr>
          <td>■顧客番号</td>
          <td><%=no %></td>
        </tr>
      </table>
    </div>
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
