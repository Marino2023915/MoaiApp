<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム【MoaiApp】</title>
<link href="../css/common.css" rel="stylesheet">
<script>
function generateNameFields() {
  var numberOfPeople = document.getElementById("number_of_people").value;
  var container = document.getElementById("name_fields_container");
  container.innerHTML = ""; // 以前の入力フィールドをクリア

  for (var i = 0; i < numberOfPeople; i++) {
    var input = document.createElement("input");
    input.type = "text";
    input.name = "person_name_" + i;
    input.placeholder = "名前 " + (i + 1);
    container.appendChild(input);
    container.appendChild(document.createElement("br")); // 改行を追加
  }
}
</script>
</head>
<body>
  <!-- ヘッダ -->
  <header>
    <div class="contents-wrap-title">
      <div class="site-title">
        <h1>MoaiApp</h1>
        <span class="site-subtitle"></span>
      </div>
      模合管理システムです

    </div>
    <hr class="hr-header">
  </header>
<!-- メインコンテンツ" -->
  <main>
    <h1>NEW画面登録</h1>

    <form action="your-server-endpoint" method="post">
      <div>
        <label for="customer_name">模合グループ名:</label>
        <input type="text" id="customer_name" name="customer_name">
      </div>
      <div>
        <label for="amount">毎月の金額:</label>
        <input type="number" id="amount" name="amount">
      </div>
      <div>
        <label for="date">いつから開始:</label>
        <input type="date" id="date" name="date">
      </div>

      <div>
        <label for="note">備考:</label>
        <textarea id="note" name="note"></textarea>
      </div>

      <input type="submit" value="登録">
    </form>

  </main>

  <main>
    <!-- その他のフォームフィールド... -->

    <div>
      <label for="number_of_people">人数:</label>
      <select id="number_of_people" name="number_of_people" onchange="generateNameFields()">
        <option value="">選択してください</option>
        <option value="1">1人</option>
        <option value="2">2人</option>
        <option value="3">3人</option>
        <!-- 必要なだけオプションを追加 -->
      </select>
    </div>

    <div id="name_fields_container">
      <!-- JavaScriptによって生成された名前入力フィールドがここに挿入される -->
    </div>

    <input type="submit" value="登録">
  </form>

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