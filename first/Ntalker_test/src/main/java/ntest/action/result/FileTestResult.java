package ntest.action.result;

public class FileTestResult {

	//客服端上传下载状态
	public boolean KF_FILE_UPLOAD ;
	public boolean KF_FILE_DOWNLOAD ;

	////访客端上传下载状态
	public boolean WEB_FILE_UPLOAD ;
	public boolean WEB_FILE_DOWNLOAD ;

	private String _url ;
	private long _filesize;

	public void setUrl(String url)
	{
		_url = url;
	}
	public void setFileSize(long size)
	{
		_filesize = size;
	}
	public String getUrl()
	{
		return _url;
	}
	public long getFileSize()
	{
		return _filesize;
	}
	
}
