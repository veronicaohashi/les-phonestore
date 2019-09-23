package les.domain.sale;

import java.util.List;

import les.domain.DomainEntity;

public class Payment extends DomainEntity {
	private List<PaymentData> paymentDatas;

	public List<PaymentData> getPaymentDatas() {
		return paymentDatas;
	}

	public void setPaymentDatas(List<PaymentData> paymentDatas) {
		this.paymentDatas = paymentDatas;
	}
}
