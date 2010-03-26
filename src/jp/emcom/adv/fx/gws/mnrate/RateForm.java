package jp.emcom.adv.fx.gws.mnrate;

import java.math.BigDecimal;




public class RateForm {
	
	private String partyId;
	private String 	currencyPair;
	private BigDecimal buyPrice ;
	private BigDecimal sellPrice ;
	
	private BigDecimal pips ;
	
	private int no;
	
	
	/**
	 * @return the pips
	 */
	public BigDecimal getPips() {
		return pips;
	}
	/**
	 * @param pips the pips to set
	 */
	public void setPips(BigDecimal pips) {
		this.pips = pips;
	}
	/**
	 * @return the partyId
	 */
	public String getPartyId() {
		return partyId;
	}
	/**
	 * @param partyId the partyId to set
	 */
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	/**
	 * @return the currencyPair
	 */
	public String getCurrencyPair() {
		return currencyPair;
	}
	/**
	 * @param currencyPair the currencyPair to set
	 */
	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}
	/**
	 * @return the buyPrice
	 */
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	/**
	 * @param buyPrice the buyPrice to set
	 */
	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	/**
	 * @return the sellPrice
	 */
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	/**
	 * @param sellPrice the sellPrice to set
	 */
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	/**
	 * @return the no
	 */
	public int getNo() {
		return no;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(int no) {
		this.no = no;
	}

	
	
	
	
}
