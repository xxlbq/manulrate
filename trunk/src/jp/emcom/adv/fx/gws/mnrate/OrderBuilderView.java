
package jp.emcom.adv.fx.gws.mnrate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jp.emcom.adv.fx.gws.ui.SWTResourceManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

import cn.bestwiz.jhf.core.jms.SimpleSender;
import cn.bestwiz.jhf.core.jms.bean.CpSpotRateInfo;
import cn.bestwiz.jhf.core.jms.bean.RateBandInfo;
import cn.bestwiz.jhf.core.jms.exception.JMSException;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class OrderBuilderView extends org.eclipse.swt.widgets.Composite {

	private Menu menu1;
	private Combo pips_combo2;
	private Label pips_label2;
	private Combo sellPrice_combo2;
	private Label sellPrice_label2;
	private Combo buyPrice_combo2;
	private Label label7;
	private Combo cpp_combo2;
	private Label label6;
	private Combo partyId_combo2;
	private Label label2;
	private Label sellPrice_label1;
	private Label party_label2;
	private Label label4;
	private Combo cpp_combo1;
	private Label label3;
	private Combo buyPrice_combo1;
	private Button doOpenRate_button2;
	private Combo sellPrice_combo1;
	private Button doOpenRate_button1;
	private Combo partyId_combo1;
	private Label party_label1;
	private ProgressBar order_progressBar;
	private Label process_label;
	private Label side_label;
	private Label currencyPair_label;
	private Group group2;
	private MenuItem aboutMenuItem;
	private MenuItem contentsMenuItem;
	private Menu helpMenu;
	private MenuItem helpMenuItem;
	private Composite composite1;
	private MenuItem exitMenuItem;
	private MenuItem closeFileMenuItem;
	private MenuItem saveFileMenuItem;
	private MenuItem newFileMenuItem;
	private MenuItem openFileMenuItem;
	private Menu fileMenu;
	private MenuItem fileMenuItem;

	
	//=================
	private SimpleSender sender = null;
	private static final Map<String,CpInfo> cpSenders = new HashMap<String,CpInfo>();
	private static Object lock = new Object();
	
//	private int orderProcessInit = 0;
	private int orderProcessMin = 0;
	private int orderProcessMax = 0;
	public static int orderPrcoessing = 0;
	private boolean processOver = false;
	public static final int nPerOrder = 10 ;
	
	//
	public static int scaling = 1;
	public static int bindCustomerSize = 1;
	private Combo pips_combo1;
	private Label pips_label1;
	//=================
	public static boolean alreadyInit = false;
	
	{
		//Register as a resource user - SWTResourceManager will
		//handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	public OrderBuilderView(Composite parent, int style) {
		super(parent, style);
		initGUI();
		
		//init order stuff
//		initOrderBuilder();
	}
	
//	private void initOrderBuilder() {
//		
//		try {
//			sender = SimpleSender.getInstance(DestinationConstant.OrderRequestQueue);
//		} catch (JMSException e) {
//			System.err.println("sender init error ! ");
//			e.printStackTrace();
//		}
//		
//	}
	
	

	/**
	* Initializes the GUI.
	*/
	@SuppressWarnings("unchecked")
	private void initGUI() {
		try {


			this.setSize(750, 406);
			this.setBackground(SWTResourceManager.getColor(192, 192, 192));
			this.addDisposeListener(new DisposeListener(){

				public void widgetDisposed(DisposeEvent de) {
					System.out.println(" widget disposed  ... fire method .");
					if(null != sender){
						
						sender.close();
						System.exit(0);
					}
					
				}
				
			});
			
			GridLayout thisLayout = new GridLayout(1, true);
			thisLayout.marginWidth = 5;
			thisLayout.marginHeight = 5;
			thisLayout.numColumns = 1;
			thisLayout.makeColumnsEqualWidth = true;
			thisLayout.horizontalSpacing = 5;
			thisLayout.verticalSpacing = 5;
			this.setLayout(thisLayout);
			{
				composite1 = new Composite(this, SWT.NONE);
				composite1.setLocation(0, 0);
				GridData composite1LayoutData = new GridData();
				composite1LayoutData.widthHint = 742;
				composite1LayoutData.heightHint = 959;
				
				composite1.setLayoutData(composite1LayoutData);
				composite1.setLayout(null);
				{
					group2 = new Group(composite1, SWT.NONE);
					group2.setLayout(null);
					group2.setBounds(21, 21, 707, 287);
					group2.setText("Rate Sender");
		{
				
				sellPrice_label2 = new Label(group2, SWT.NONE);
				sellPrice_label2.setText("sellPrice :");
				sellPrice_label2.setBounds(385, 63, 63, 14);
				sellPrice_label2.setBackground(SWTResourceManager.getColor(250,210,165));
			}
			{
				sellPrice_combo2 = new Combo(group2, SWT.NONE);
				sellPrice_combo2.setBounds(455, 58, 63, 20);
				sellPrice_combo2.add("110.005",0);
				sellPrice_combo2.select(0);
			}
			{
				label7 = new Label(group2, SWT.NONE);
				label7.setText("buyPrice :");
				label7.setBounds(245, 63, 63, 14);
			}
			{
				buyPrice_combo2 = new Combo(group2, SWT.NONE);
				buyPrice_combo2.setBounds(315, 56, 63, 20);
				buyPrice_combo2.add("110.010",0);
				buyPrice_combo2.select(0);
			}
			{
				label6 = new Label(group2, SWT.NONE);
				label6.setText("ccp :");
				label6.setBounds(140, 65, 28, 14);
			}
			{
				cpp_combo2 = new Combo(group2, SWT.READ_ONLY);
				cpp_combo2.setBounds(168, 63, 63, 20);
				cpp_combo2.add("USD/JPY",0);
				cpp_combo2.add("EUR/JPY",1);
				cpp_combo2.add("EUR/USD",2);
				cpp_combo2.select(0);
			}
			{
				label2 = new Label(group2, SWT.NONE);
				label2.setText("party id:");
				label2.setBounds(7, 28, 56, 14);
				label2
					.setBackground(SWTResourceManager.getColor(182, 254, 223));
			}
			{
				partyId_combo2 = new Combo(group2, SWT.READ_ONLY);
				partyId_combo2.setBounds(70, 60, 63, 20);
				partyId_combo2.setBackground(SWTResourceManager.getColor(182,254,223));
				partyId_combo2.add("MOCK",0);
				partyId_combo2.add("DB",1);
				partyId_combo2.add("GS",2);
				partyId_combo2.select(1);
			}

					{
						party_label1 = new Label(group2, SWT.NONE);
						party_label1.setText("party id:");
						party_label1.setBounds(7, 28, 56, 14);
						party_label1.setBackground(SWTResourceManager.getColor(182, 254, 223));
					}
					{
						partyId_combo1 = new Combo(group2, SWT.READ_ONLY);
						partyId_combo1.setBounds(70, 28, 56, 21);
						partyId_combo1.setBackground(SWTResourceManager.getColor(182, 254, 223));
						
						partyId_combo1.add("MOCK",0);
						partyId_combo1.add("DB",1);
						partyId_combo1.add("GS",2);
						partyId_combo1.select(0);
						
					}
					
					
					{
						currencyPair_label = new Label(group2, SWT.NONE);
						currencyPair_label.setText("ccp :");
						currencyPair_label.setBounds(140, 28, 28, 14);
					}

					
					{
						side_label = new Label(group2, SWT.NONE);
						side_label.setText("buyPrice :");
						side_label.setBounds(249, 28, 63, 14);
					}
					
					
					
					
					
					
					
					

					

					{
						doOpenRate_button1 = new Button(group2, SWT.PUSH
							| SWT.CENTER);
						doOpenRate_button1.setText("\u53d1\u9001");
						doOpenRate_button1.setBounds(616, 21, 77, 21);
						doOpenRate_button1.setData("ACTION_SOURCE", 1);
						doOpenRate_button1
							.addMouseListener(new SendRateAdapter());
					}
					{
						sellPrice_label1 = new Label(group2, SWT.NONE);
						sellPrice_label1.setText("sellPrice :");
						
						sellPrice_label1.setBounds(385, 28, 63, 14);
						sellPrice_label1.setBackground(SWTResourceManager.getColor(250,210,165));
					}
					{
						sellPrice_combo1 = new Combo(group2, SWT.NONE);
						sellPrice_combo1.setBounds(455, 28, 63, 21);
						
						sellPrice_combo1.add("110.005",0);
						sellPrice_combo1.select(0);
						
					}
					{
						doOpenRate_button2 = new Button(group2, SWT.PUSH | SWT.CENTER);
						doOpenRate_button2.setText("\u53d1\u9001");
						doOpenRate_button2.setBounds(616, 56, 77, 21);
						doOpenRate_button2.setData("ACTION_SOURCE", 2);
						doOpenRate_button2
							.addMouseListener(new SendRateAdapter());
					}
					{
						buyPrice_combo1 = new Combo(group2, SWT.NONE);
						buyPrice_combo1.setBounds(315, 28, 63, 21);
						buyPrice_combo1.add("110.010",0);
						buyPrice_combo1.select(0);
					}
					{
						label3 = new Label(group2, SWT.NONE);
						label3.setText("buyPrice :");
						label3.setBounds(249, 28, 63, 14);
					}
					{
						cpp_combo1 = new Combo(group2, SWT.READ_ONLY);
						cpp_combo1.setBounds(168, 28, 63, 21);
						cpp_combo1.add("USD/JPY",0);
						cpp_combo1.add("EUR/JPY",1);
						cpp_combo1.add("EUR/USD",2);
						cpp_combo1.select(0);
					}
					{
						label4 = new Label(group2, SWT.NONE);
						label4.setText("ccp :");
						label4.setBounds(140, 28, 28, 14);
					}
					{
						party_label2 = new Label(group2, SWT.NONE);
						party_label2.setText("party id:");
						party_label2.setBounds(7, 64, 56, 14);
						party_label2.setBackground(SWTResourceManager.getColor(
							182,
							254,
							223));
					}
				}
				{
					process_label = new Label(composite1, SWT.NONE);
					process_label.setText("processing ... ");
					process_label.setBounds(7, 720, 63, 14);
					process_label.setVisible(false);
					
				}
				{
					order_progressBar = new ProgressBar(composite1, SWT.HORIZONTAL | SWT.SMOOTH);
					order_progressBar.setBounds(7, 749, 728, 14);
					order_progressBar.setMinimum(orderProcessMin);
//					order_progressBar.setMaximum(orderProcessMax);
					order_progressBar.setVisible(false);
					
					
					
					
					
//					Display.getDefault().syncExec(new Runnable(){
//						public void run(){
//							
//							while(true){
//								try {
//							        Thread.sleep(1000);
//							        
//							        if(order_progressBar.getSelection() >= 10 ){
//							        	System.out.println(" process is over.");
//							        	process_label.setText(" process finished .");
//							        }
//							      } catch (InterruptedException e) {
//							        // Do nothing
//							      }
//							}
//							
//							
//						}
//					}
//					);
					
				}
				
				{
					pips_combo2 = new Combo(group2, SWT.NONE);
					pips_combo2.setBounds(560, 56, 35, 21);
					pips_combo2.setBackground(SWTResourceManager.getColor(182,254,223));
					
					pips_combo2.add("5",0);
					pips_combo2.add("15",1);
					pips_combo2.add("30",2);
					pips_combo2.add("50",3);
					pips_combo2.select(2);
					
				}
				{
					pips_label2 = new Label(group2, SWT.NONE);
					pips_label2.setText("pips:");
					pips_label2.setBounds(532, 63, 28, 14);
				}
				{
					pips_label1 = new Label(group2, SWT.NONE);
					pips_label1.setText("pips:");
					pips_label1.setBounds(532, 28, 28, 14);
				}
				{
					pips_combo1 = new Combo(group2, SWT.NONE);
					pips_combo1.setBounds(560, 28, 35, 21);
					
					pips_combo1.add("5",0);
					pips_combo1.add("15",1);
					pips_combo1.add("30",2);
					pips_combo1.add("50",3);
					pips_combo1.select(0);
					
					pips_combo1.setBackground(SWTResourceManager.getColor(182,254,223));
				}
			}
			{
				menu1 = new Menu(getShell(), SWT.BAR);
				getShell().setMenuBar(menu1);
				{
					fileMenuItem = new MenuItem(menu1, SWT.CASCADE);
					fileMenuItem.setText("File");
					{
						fileMenu = new Menu(fileMenuItem);
						{
							openFileMenuItem = new MenuItem(fileMenu, SWT.CASCADE);
							openFileMenuItem.setText("Open");
						}
						{
							newFileMenuItem = new MenuItem(fileMenu, SWT.CASCADE);
							newFileMenuItem.setText("New");
						}
						{
							saveFileMenuItem = new MenuItem(fileMenu, SWT.CASCADE);
							saveFileMenuItem.setText("Save");
						}
						{
							closeFileMenuItem = new MenuItem(fileMenu, SWT.CASCADE);
							closeFileMenuItem.setText("Close");
						}
						{
							exitMenuItem = new MenuItem(fileMenu, SWT.CASCADE);
							exitMenuItem.setText("Exit");
						}
						fileMenuItem.setMenu(fileMenu);
					}
				}
				{
					helpMenuItem = new MenuItem(menu1, SWT.CASCADE);
					helpMenuItem.setText("Help");
					{
						helpMenu = new Menu(helpMenuItem);
						{
							contentsMenuItem = new MenuItem(helpMenu, SWT.CASCADE);
							contentsMenuItem.setText("Contents");
						}
						{
							aboutMenuItem = new MenuItem(helpMenu, SWT.CASCADE);
							aboutMenuItem.setText("About");
						}
						helpMenuItem.setMenu(helpMenu);
					}
				}
			}
			
			
			this.layout();
			
			initDatas();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	private void initDatas() {
		try {
			cpSenders.put("DB", new CpInfo("DB", "topic/gwCounterPartyDBRateTopic", "queue/gwDbOrderRequestQueue"));
//      	cps.put("DR", new CpInfo("DR", "topic/gwCounterPartyDRRateTopic", "queue/gwDrOrderRequestQueue"));
			cpSenders.put("GS", new CpInfo("GS", "topic/gwCounterPartyGSRateTopic", "queue/gwGsOrderRequestQueue"));
			cpSenders.put("MOCK", new CpInfo("MOCK", "topic/gwCounterPartyMOCKRateTopic", "queue/gwMockOrderRequestQueue"));
		} catch (JMSException e) {
			
			e.printStackTrace();
		}

		
	}

	protected void submitRateForm(RateForm of,Integer no) {
		
		String 	currencyPair = null;
		String 	partyId = null;
		BigDecimal buy = null;
		BigDecimal sell = null;
		BigDecimal pips = null;
		switch (no) {
		case 1:
			
			 currencyPair 			= this.getCpp_combo1().getText();
			 partyId 			= this.getPartyId_combo1().getText();
			 buy = new BigDecimal(this.getBuyPrice_combo1().getText());
			 sell =new BigDecimal(this.getSellPrice_combo1().getText()); ;
			 pips =new BigDecimal(this.getPips_combo1().getText()); ; 
			 
			 
			of.setCurrencyPair(currencyPair);
			of.setPartyId(partyId);
			of.setBuyPrice(buy);
			of.setSellPrice(sell);
			of.setPips(pips);
			break;
			
		case 2:
			
			 currencyPair 			= this.getCpp_combo2().getText();
			 partyId 			= this.getPartyId_combo2().getText();
			 buy = new BigDecimal(this.getBuyPrice_combo2().getText());
			 sell =new BigDecimal(this.getSellPrice_combo2().getText()); ;
			 pips =new BigDecimal(this.getPips_combo2().getText()); ; 
			 
			of.setCurrencyPair(currencyPair);
			of.setPartyId(partyId);
			of.setBuyPrice(buy);
			of.setSellPrice(sell);
			of.setPips(pips);
			break;
			
		default:
			break;
		}

	
	}

	
	
	
	private void showProcessStuff() {
		this.process_label.setVisible(true);
		this.order_progressBar.setVisible(true);
		
	}
	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void main(String[] args) {
		
		Display display = Display.getDefault();
		Shell shell = new Shell(display ,SWT.CLOSE |SWT.MIN |SWT.MAX |SWT.TITLE);

		OrderBuilderView inst = new OrderBuilderView(shell, SWT.SYSTEM_MODAL);
		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
		shell.layout();
		if(size.x == 0 && size.y == 0) {
//			inst.pack();
//			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		shell.open();
		
		
		
//		Display.getCurrent(display.asyncExec(new Runnable() {
//			public void run() {
//			//Inform the indicator that some amount of work has been done
//			indicator.worked(1);
//			}
//			}));
//
//		
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		
		
		
	}
	
	private void doOpenOrder_buttonMouseDown(RateForm of) {
		
		System.out.println("button fire .....");
		final RateForm form = of;
		new Thread() {
			public void run() {

//				Display.getDefault().asyncExec(new Runnable() {
//					public void run() {
//						showProcessStuff();
////						new OrderRuningOperation().start();
//						new LongRunningOperation().start();
//					}
//
//				});
				
				doUIOpenRate(form);
				
			}
			
		}.start();

	


		

		
	}



	/**
	 * 
	 *         cps.put("DB", new CpInfo("DB", "topic/gwCounterPartyDBRateTopic", "queue/gwDbOrderRequestQueue"));
//        cps.put("DR", new CpInfo("DR", "topic/gwCounterPartyDRRateTopic", "queue/gwDrOrderRequestQueue"));
        cps.put("GS", new CpInfo("GS", "topic/gwCounterPartyGSRateTopic", "queue/gwGsOrderRequestQueue"));
        cps.put("MOCK", new CpInfo("MOCK", "topic/gwCounterPartyMOCKRateTopic", "queue/gwMockOrderRequestQueue"));

	 * 
	 * @author lubq <lubq@adv.emcom.jp>
	 * @param of
	 */
	protected void doUIOpenRate(RateForm of) {
		
		try {
			sender = cpSenders.get(of.getPartyId()).getRateSender();
			
			CpSpotRateInfo r = makeCpRateInfo(of.getPartyId(),of.getCurrencyPair(),of.getBuyPrice(),of.getSellPrice()
					,of.getPips());
			System.out.println("============ sending. .. "+r);
			sender.sendMessage(r);
			System.out.println("============ send over  .");
		} catch (JMSException e1) {
			e1.printStackTrace();
		}

		
	}

    public static CpSpotRateInfo makeCpRateInfo(String pid,String ccp
    		,BigDecimal askPrice,BigDecimal bidPrice,BigDecimal pips){

        CpSpotRateInfo r = new CpSpotRateInfo();
        
        r.setCounterPartyId(pid);
        r.setUsualable(true);
        r.setCurrencyPair(ccp);
        r.setMessageTime(new Date());
        r.setInManualStatus(1);
        r.setFxPriceId(UUID.randomUUID().toString());
        r.setBookingType(1);
        
        RateBandInfo rbiask = new RateBandInfo();
        RateBandInfo rbibid = new RateBandInfo();
        
        rbiask.setPriceId(UUID.randomUUID().toString());
        rbibid.setPriceId(UUID.randomUUID().toString());

        BigDecimal dopips = pips.divide(new BigDecimal(Math.pow(10, 3)),3, RoundingMode.UP);
      
        rbiask.setRate((askPrice).add( dopips).setScale(3, RoundingMode.UP));
        rbibid.setRate((bidPrice).subtract(dopips).setScale(3, RoundingMode.UP));
//   
        
        r.setAskBandInfoList(rbiask);
        r.setBidBandInfoList(rbibid);
        
        
        return r;
    }
	
	/**
	 * This class simulates a long-running operation
	 */
	class LongRunningOperation extends Thread {

		public LongRunningOperation() {
		}

		public void run() {
			
//			while (orderProcessInit < 10) {
//
//				increaseOrderProcess(orderProcessInit);
//				
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//
//					e.printStackTrace();
//				}
//			}

			// Perform work here--this operation just sleeps
			while (orderPrcoessing <= orderProcessMax) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						
						if (order_progressBar.getSelection() >= orderProcessMax) {
							// 通知前台线程 改变 label
							System.out.println("process bar will finished .....");
							process_label.setText("finished ");
							processOver = true;
//							return;
						}

						
						// Increment the progress bar
						order_progressBar.setSelection(getOrderProcess());
						System.out.println(" progressBar.getSelection()="+ order_progressBar.getSelection()
								+ " , orderProcessMax ="+orderProcessMax);

					}
				});
				

				if(processOver){
					orderPrcoessing = 0;
					break;
				}
			}
		}
	}
	
	
// /**
// * This class simulates a long-running operation
// */
// class OrderRuningOperation extends Thread {
// // private Display display;
// // private ProgressBar progressBar;
////	  private Label label;
////	  int processNumber ;
//	  public OrderRuningOperation() {
////	    this.display = display;
////	    this.progressBar = app.getOrder_progressBar();
////	    this.label = app.getProcess_label();
////		  this.processNumber = orderProcess;
//	  }
//	  
//	  public void run() {
//		  while(orderProcessInit < 10){
//			  
//			  increaseOrderProcess(orderProcessInit);
//			  try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//
//				e.printStackTrace();
//			}
//		  }
//			  
//	  }
//	  
//
//	}






	public Combo getPartyId_combo1() {
		return partyId_combo1;
	}

	public void setPartyId_combo1(Combo executionType_combo) {
		this.partyId_combo1 = executionType_combo;
	}






	



	public ProgressBar getOrder_progressBar() {
		return order_progressBar;
	}

	public void setOrder_progressBar(ProgressBar order_progressBar) {
		this.order_progressBar = order_progressBar;
	}

	public Label getProcess_label() {
		return process_label;
	}

	public void setProcess_label(Label process_label) {
		this.process_label = process_label;
	}

	public int getOrderProcess() {
		return orderPrcoessing;
	}

	public static void increaseOrderProcess(int increase) {
		synchronized (lock) {
			OrderBuilderView.orderPrcoessing = OrderBuilderView.orderPrcoessing + increase;
			System.out.println("orderPrcoessing   ==== :"+orderPrcoessing);
		}

	}
	
	private void doOrderAndSettle_buttonMouseUp(MouseEvent evt) {
		System.out.println("doOrderAndSettle_button.mouseUp, event=" + evt);
		//TODO add your code for doOrderAndSettle_button.mouseUp
	}
	
	
	
	class SendRateAdapter extends MouseAdapter{

		@Override
		public void mouseDoubleClick(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseDoubleClick(e);
		}

		@Override
		public void mouseDown(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseDown(e);
		}



			
		@Override
		public void mouseUp(MouseEvent e) {
			
			RateForm of = new RateForm();
			// System.out.println("TEXT:"+customerIdlist_list.getText());
			submitRateForm(of,(Integer)((Button)e.getSource()).getData("ACTION_SOURCE"));

//				orderProcessMax = nPerOrder;
//				order_progressBar.setMaximum(orderProcessMax);
//
//			System.out.println("===============> Maximum:" + orderProcessMax
//					+ ",orderprocessing:" + orderPrcoessing);

			doOpenOrder_buttonMouseDown(of);

		}

	}


	/**
	 * @return the sellPrice_combo2
	 */
	public Combo getSellPrice_combo2() {
		return sellPrice_combo2;
	}

	/**
	 * @param sellPrice_combo2 the sellPrice_combo2 to set
	 */
	public void setSellPrice_combo2(Combo sellPrice_combo2) {
		this.sellPrice_combo2 = sellPrice_combo2;
	}

	/**
	 * @return the buyPrice_combo2
	 */
	public Combo getBuyPrice_combo2() {
		return buyPrice_combo2;
	}

	/**
	 * @param buyPrice_combo2 the buyPrice_combo2 to set
	 */
	public void setBuyPrice_combo2(Combo buyPrice_combo2) {
		this.buyPrice_combo2 = buyPrice_combo2;
	}

	/**
	 * @return the cpp_combo2
	 */
	public Combo getCpp_combo2() {
		return cpp_combo2;
	}

	/**
	 * @param cpp_combo2 the cpp_combo2 to set
	 */
	public void setCpp_combo2(Combo cpp_combo2) {
		this.cpp_combo2 = cpp_combo2;
	}

	/**
	 * @return the partyId_combo2
	 */
	public Combo getPartyId_combo2() {
		return partyId_combo2;
	}

	/**
	 * @param partyId_combo2 the partyId_combo2 to set
	 */
	public void setPartyId_combo2(Combo partyId_combo2) {
		this.partyId_combo2 = partyId_combo2;
	}

	/**
	 * @return the menu1
	 */
	public Menu getMenu1() {
		return menu1;
	}

	/**
	 * @param menu1 the menu1 to set
	 */
	public void setMenu1(Menu menu1) {
		this.menu1 = menu1;
	}

	/**
	 * @return the sellPrice_label1
	 */
	public Label getSellPrice_label1() {
		return sellPrice_label1;
	}

	/**
	 * @param sellPrice_label1 the sellPrice_label1 to set
	 */
	public void setSellPrice_label1(Label label1) {
		this.sellPrice_label1 = label1;
	}

	/**
	 * @return the party_label2
	 */
	public Label getParty_label2() {
		return party_label2;
	}

	/**
	 * @param party_label2 the party_label2 to set
	 */
	public void setParty_label2(Label label5) {
		this.party_label2 = label5;
	}

	/**
	 * @return the label4
	 */
	public Label getLabel4() {
		return label4;
	}

	/**
	 * @param label4 the label4 to set
	 */
	public void setLabel4(Label label4) {
		this.label4 = label4;
	}

	/**
	 * @return the cpp_combo1
	 */
	public Combo getCpp_combo1() {
		return cpp_combo1;
	}

	/**
	 * @param cpp_combo1 the cpp_combo1 to set
	 */
	public void setCpp_combo1(Combo cpp_combo1) {
		this.cpp_combo1 = cpp_combo1;
	}

	/**
	 * @return the label3
	 */
	public Label getLabel3() {
		return label3;
	}

	/**
	 * @param label3 the label3 to set
	 */
	public void setLabel3(Label label3) {
		this.label3 = label3;
	}

	/**
	 * @return the buyPrice_combo1
	 */
	public Combo getBuyPrice_combo1() {
		return buyPrice_combo1;
	}

	/**
	 * @param buyPrice_combo1 the buyPrice_combo1 to set
	 */
	public void setBuyPrice_combo1(Combo buyPrice_combo1) {
		this.buyPrice_combo1 = buyPrice_combo1;
	}

	/**
	 * @return the doOpenRate_button2
	 */
	public Button getDoOpenRate_button2() {
		return doOpenRate_button2;
	}

	/**
	 * @param doOpenRate_button2 the doOpenRate_button2 to set
	 */
	public void setDoOpenRate_button2(Button button1) {
		this.doOpenRate_button2 = button1;
	}

	/**
	 * @return the sellPrice_combo1
	 */
	public Combo getSellPrice_combo1() {
		return sellPrice_combo1;
	}

	/**
	 * @param sellPrice_combo1 the sellPrice_combo1 to set
	 */
	public void setSellPrice_combo1(Combo sellPrice_combo1) {
		this.sellPrice_combo1 = sellPrice_combo1;
	}

	/**
	 * @return the doOpenRate_button1
	 */
	public Button getDoOpenRate_button1() {
		return doOpenRate_button1;
	}

	/**
	 * @param doOpenRate_button1 the doOpenRate_button1 to set
	 */
	public void setDoOpenRate_button1(Button doOpenRate_button1) {
		this.doOpenRate_button1 = doOpenRate_button1;
	}

	/**
	 * @return the party_label1
	 */
	public Label getParty_label1() {
		return party_label1;
	}

	/**
	 * @param party_label1 the party_label1 to set
	 */
	public void setParty_label1(Label executionType_label) {
		this.party_label1 = executionType_label;
	}

	/**
	 * @return the side_label
	 */
	public Label getSide_label() {
		return side_label;
	}

	/**
	 * @param side_label the side_label to set
	 */
	public void setSide_label(Label side_label) {
		this.side_label = side_label;
	}

	/**
	 * @return the currencyPair_label
	 */
	public Label getCurrencyPair_label() {
		return currencyPair_label;
	}

	/**
	 * @param currencyPair_label the currencyPair_label to set
	 */
	public void setCurrencyPair_label(Label currencyPair_label) {
		this.currencyPair_label = currencyPair_label;
	}

	/**
	 * @return the group2
	 */
	public Group getGroup2() {
		return group2;
	}

	/**
	 * @param group2 the group2 to set
	 */
	public void setGroup2(Group group2) {
		this.group2 = group2;
	}

	/**
	 * @return the aboutMenuItem
	 */
	public MenuItem getAboutMenuItem() {
		return aboutMenuItem;
	}

	/**
	 * @param aboutMenuItem the aboutMenuItem to set
	 */
	public void setAboutMenuItem(MenuItem aboutMenuItem) {
		this.aboutMenuItem = aboutMenuItem;
	}

	/**
	 * @return the contentsMenuItem
	 */
	public MenuItem getContentsMenuItem() {
		return contentsMenuItem;
	}

	/**
	 * @param contentsMenuItem the contentsMenuItem to set
	 */
	public void setContentsMenuItem(MenuItem contentsMenuItem) {
		this.contentsMenuItem = contentsMenuItem;
	}

	/**
	 * @return the helpMenu
	 */
	public Menu getHelpMenu() {
		return helpMenu;
	}

	/**
	 * @param helpMenu the helpMenu to set
	 */
	public void setHelpMenu(Menu helpMenu) {
		this.helpMenu = helpMenu;
	}

	/**
	 * @return the helpMenuItem
	 */
	public MenuItem getHelpMenuItem() {
		return helpMenuItem;
	}

	/**
	 * @param helpMenuItem the helpMenuItem to set
	 */
	public void setHelpMenuItem(MenuItem helpMenuItem) {
		this.helpMenuItem = helpMenuItem;
	}

	/**
	 * @return the composite1
	 */
	public Composite getComposite1() {
		return composite1;
	}

	/**
	 * @param composite1 the composite1 to set
	 */
	public void setComposite1(Composite composite1) {
		this.composite1 = composite1;
	}

	/**
	 * @return the exitMenuItem
	 */
	public MenuItem getExitMenuItem() {
		return exitMenuItem;
	}

	/**
	 * @param exitMenuItem the exitMenuItem to set
	 */
	public void setExitMenuItem(MenuItem exitMenuItem) {
		this.exitMenuItem = exitMenuItem;
	}

	/**
	 * @return the closeFileMenuItem
	 */
	public MenuItem getCloseFileMenuItem() {
		return closeFileMenuItem;
	}

	/**
	 * @param closeFileMenuItem the closeFileMenuItem to set
	 */
	public void setCloseFileMenuItem(MenuItem closeFileMenuItem) {
		this.closeFileMenuItem = closeFileMenuItem;
	}

	/**
	 * @return the saveFileMenuItem
	 */
	public MenuItem getSaveFileMenuItem() {
		return saveFileMenuItem;
	}

	/**
	 * @param saveFileMenuItem the saveFileMenuItem to set
	 */
	public void setSaveFileMenuItem(MenuItem saveFileMenuItem) {
		this.saveFileMenuItem = saveFileMenuItem;
	}

	/**
	 * @return the newFileMenuItem
	 */
	public MenuItem getNewFileMenuItem() {
		return newFileMenuItem;
	}

	/**
	 * @param newFileMenuItem the newFileMenuItem to set
	 */
	public void setNewFileMenuItem(MenuItem newFileMenuItem) {
		this.newFileMenuItem = newFileMenuItem;
	}

	/**
	 * @return the openFileMenuItem
	 */
	public MenuItem getOpenFileMenuItem() {
		return openFileMenuItem;
	}

	/**
	 * @param openFileMenuItem the openFileMenuItem to set
	 */
	public void setOpenFileMenuItem(MenuItem openFileMenuItem) {
		this.openFileMenuItem = openFileMenuItem;
	}

	/**
	 * @return the fileMenu
	 */
	public Menu getFileMenu() {
		return fileMenu;
	}

	/**
	 * @param fileMenu the fileMenu to set
	 */
	public void setFileMenu(Menu fileMenu) {
		this.fileMenu = fileMenu;
	}

	/**
	 * @return the fileMenuItem
	 */
	public MenuItem getFileMenuItem() {
		return fileMenuItem;
	}

	/**
	 * @param fileMenuItem the fileMenuItem to set
	 */
	public void setFileMenuItem(MenuItem fileMenuItem) {
		this.fileMenuItem = fileMenuItem;
	}

	/**
	 * @return the sender
	 */
	public SimpleSender getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(SimpleSender sender) {
		this.sender = sender;
	}

	/**
	 * @return the lock
	 */
	public static Object getLock() {
		return lock;
	}

	/**
	 * @param lock the lock to set
	 */
	public static void setLock(Object lock) {
		OrderBuilderView.lock = lock;
	}

	/**
	 * @return the orderProcessMin
	 */
	public int getOrderProcessMin() {
		return orderProcessMin;
	}

	/**
	 * @param orderProcessMin the orderProcessMin to set
	 */
	public void setOrderProcessMin(int orderProcessMin) {
		this.orderProcessMin = orderProcessMin;
	}

	/**
	 * @return the orderProcessMax
	 */
	public int getOrderProcessMax() {
		return orderProcessMax;
	}

	/**
	 * @param orderProcessMax the orderProcessMax to set
	 */
	public void setOrderProcessMax(int orderProcessMax) {
		this.orderProcessMax = orderProcessMax;
	}

	/**
	 * @return the orderPrcoessing
	 */
	public static int getOrderPrcoessing() {
		return orderPrcoessing;
	}

	/**
	 * @param orderPrcoessing the orderPrcoessing to set
	 */
	public static void setOrderPrcoessing(int orderPrcoessing) {
		OrderBuilderView.orderPrcoessing = orderPrcoessing;
	}

	/**
	 * @return the processOver
	 */
	public boolean isProcessOver() {
		return processOver;
	}

	/**
	 * @param processOver the processOver to set
	 */
	public void setProcessOver(boolean processOver) {
		this.processOver = processOver;
	}

	/**
	 * @return the scaling
	 */
	public static int getScaling() {
		return scaling;
	}

	/**
	 * @param scaling the scaling to set
	 */
	public static void setScaling(int scaling) {
		OrderBuilderView.scaling = scaling;
	}

	/**
	 * @return the bindCustomerSize
	 */
	public static int getBindCustomerSize() {
		return bindCustomerSize;
	}

	/**
	 * @param bindCustomerSize the bindCustomerSize to set
	 */
	public static void setBindCustomerSize(int bindCustomerSize) {
		OrderBuilderView.bindCustomerSize = bindCustomerSize;
	}

	/**
	 * @return the alreadyInit
	 */
	public static boolean isAlreadyInit() {
		return alreadyInit;
	}

	/**
	 * @param alreadyInit the alreadyInit to set
	 */
	public static void setAlreadyInit(boolean alreadyInit) {
		OrderBuilderView.alreadyInit = alreadyInit;
	}

	/**
	 * @return the nPerOrder
	 */
	public static int getNPerOrder() {
		return nPerOrder;
	}

	/**
	 * @return the sellPrice_label2
	 */
	public Label getSellPrice_label2() {
		return sellPrice_label2;
	}

	/**
	 * @param sellPrice_label2 the sellPrice_label2 to set
	 */
	public void setSellPrice_label2(Label label8) {
		this.sellPrice_label2 = label8;
	}

	/**
	 * @return the label7
	 */
	public Label getLabel7() {
		return label7;
	}

	/**
	 * @param label7 the label7 to set
	 */
	public void setLabel7(Label label7) {
		this.label7 = label7;
	}

	/**
	 * @return the label6
	 */
	public Label getLabel6() {
		return label6;
	}

	/**
	 * @param label6 the label6 to set
	 */
	public void setLabel6(Label label6) {
		this.label6 = label6;
	}

	/**
	 * @return the label2
	 */
	public Label getLabel2() {
		return label2;
	}

	/**
	 * @param label2 the label2 to set
	 */
	public void setLabel2(Label label2) {
		this.label2 = label2;
	}

	/**
	 * @return the pips_combo2
	 */
	public Combo getPips_combo2() {
		return pips_combo2;
	}

	/**
	 * @param pips_combo2 the pips_combo2 to set
	 */
	public void setPips_combo2(Combo pips_combo2) {
		this.pips_combo2 = pips_combo2;
	}

	/**
	 * @return the pips_combo1
	 */
	public Combo getPips_combo1() {
		return pips_combo1;
	}

	/**
	 * @param pips_combo1 the pips_combo1 to set
	 */
	public void setPips_combo1(Combo pips_combo1) {
		this.pips_combo1 = pips_combo1;
	}

	
	

}
