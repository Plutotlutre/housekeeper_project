
public class app {
	private int appId;
	private String appName;
	private String appIcon;//Ӧ��ͼ���ַ
	private boolean ableApp;
	public app() {
		
	}
	
	public app(int appId, String appName, String appIcon) {
		super();
		this.appId = appId;
		this.appName = appName;
		this.appIcon = appIcon;
		this.ableApp = true;//Ĭ��Ӧ���ǿ��Խ�����Ϣ�ģ�Ĭ��Ϊtrue�����ú�����ʱ����������Ϊfalse������ʾ��Ӧ����Ϣ
	}
		

	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppIcon() {
		return appIcon;
	}
	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}
	public boolean isAbleApp() {
		return ableApp;
	}
	public void setAbleApp(boolean ableApp) {
		this.ableApp = ableApp;
	}

}
