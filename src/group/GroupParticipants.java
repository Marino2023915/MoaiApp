/**
 * Copyright nus Academy Co., Ltd. 2008, 2020. All rights reserved.
 *
 * This software is the proprietary information of nus Academy Co., Ltd.
 * Use is subject to license terms.
 */
package group;

/**
 * 顧客情報を保持します。
 * <br><br>
 * 【Information】<br>
 *  機能１ すべてのメンバ変数，メソッドを作成します。<br>
 *
 * @author nus Academy Co., Ltd
 */
public class GroupParticipants {
    /** グループid */
    private Integer group_No;

    /** 顧客番号 */
    private String no;

	public Integer getGroup_No() {
		return group_No;
	}

	public void setGroup_No(Integer group_No) {
		this.group_No = group_No;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}







}
