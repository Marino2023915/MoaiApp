/**
 * Copyright nus Academy Co., Ltd. 2008, 2020. All rights reserved.
 *
 * This software is the proprietary information of nus Academy Co., Ltd.
 * Use is subject to license terms.
 */
package payment;

import java.time.LocalDate;
import java.util.List;

/**
 * 支払い情報を保持します。
 * <br><br>
 * 【Information】<br>
 *  機能１ すべてのメンバ変数，メソッドを作成します。<br>
 *
 * @author nus Academy Co., Ltd
 */
public class Payment {
    /** 模合グループ名 */
    private String group_Name;

    /** 毎月の支払い金額 */
    private int amount_of_Money;



	/** 支払い開始日 */
    private LocalDate date_of_Payment;

    /** 備考 */
    private String remarks;
    /** 参加者の名前のリスト */
    private List<String> participantNames;

    public String getGroup_Name() {
		return group_Name;
	}

	public void setGroup_Name(String group_Name) {
		this.group_Name = group_Name;
	}

	public int getAmount_of_Money() {
		return amount_of_Money;
	}

	public void setAmount_of_Money(int amount_of_Money) {
		this.amount_of_Money = amount_of_Money;
	}

	public LocalDate getDate_of_Payment() {
		return date_of_Payment;
	}

	public void setDate_of_Payment(LocalDate date_of_Payment) {
		this.date_of_Payment = date_of_Payment;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<String> getParticipantNames() {
		return participantNames;
	}

	public void setParticipantNames(List<String> participantNames) {
		this.participantNames = participantNames;
	}



}
