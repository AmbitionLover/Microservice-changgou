import com.changgou.util.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.net.InetSocketAddress;
import java.util.Optional;

/**
 * @author Ambi
 * @version 1.0
 * @date 2020/3/12 20:22
 */
public class FastdfsClientTest {
    /**
     * 文件上传
     *
     * @throws Exception
     */
    @Test
    public void upload() throws Exception {
        String filePath = new ClassPathResource("fdfs_client.conf").getPath();
        //加载全局的配置文件
        ClientGlobal.init(filePath);

        //创建TrackerClient客户端对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient对象获取TrackerServer信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取StorageClient对象
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //执行文件上传
        String[] jpgs = storageClient.upload_file("C:\\Users\\Administrator\\Pictures\\2.png", "jpg", null);

        for (String jpg : jpgs) {

            System.out.println(jpg);
        }

    }

    @Test
    public void getStorages() throws Exception{
        StorageServer group1 = FastDFSClient.getStorages("group1");

    }

    @Test
    public void getFile() throws  Exception{
        FileInfo group1 = FastDFSClient.getFile("group1", "M00/00/00/rBFizl5rnzKAeGw2AAxTc0wa1Ho396.jpg");
        System.out.println(group1.getSourceIpAddr());
    }

    //获取组相关的信息
    @Test
    public void getGroupInfo() throws Exception {
        String filePath = new ClassPathResource("fdfs_client.conf").getPath();
        //加载全局的配置文件
        ClientGlobal.init(filePath);
        //创建TrackerClient客户端对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient对象获取TrackerServer信息
        TrackerServer trackerServer = trackerClient.getConnection();

        StorageServer group1 = trackerClient.getStoreStorage(trackerServer, "group1");

        //组对应的服务器的地址  因为有可能有多个服务器.
        ServerInfo[] group1s = trackerClient.getFetchStorages(trackerServer, "group1", "M00/00/00/rBFizl5rKwWAceS0AAxTc0wa1Ho775.jpg");
        for (ServerInfo serverInfo : group1s) {
            System.out.println(serverInfo.getIpAddr());
            System.out.println(serverInfo.getPort());
        }
    }

    @Test
    public void getTrackerInfo() throws Exception {
        String filePath = new ClassPathResource("fdfs_client.conf").getPath();
        //加载全局的配置文件
        ClientGlobal.init(filePath);
        //创建TrackerClient客户端对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient对象获取TrackerServer信息
        TrackerServer trackerServer = trackerClient.getConnection();

        InetSocketAddress inetSocketAddress = trackerServer.getInetSocketAddress();
        System.out.println(inetSocketAddress);

    }

}
