package Standard.utils.tools;/*
 * package utils.tools;
 *
 * import java.io.File; import java.net.URL;
 *
 * import org.apache.commons.codec.binary.Base64; import
 * org.apache.commons.io.FileUtils;
 *
 * import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI; import
 * testlink.api.java.client.TestLinkAPIClient; import
 * testlink.api.java.client.TestLinkAPIResults; import
 * utils.evidencia.Evidencia; import utils.exceptions.ExceptionUtils; import
 * utils.readers.Config;
 *
 * public class TestLinkUtils {
 *
 * private static String devkey; private static String url;
 *
 * public static void setTestResult(String project, String build, String
 * testPlan, String ct, String note, String result) { try { devkey =
 * Config.getProperty("testlink.devkey"); url =
 * Config.getProperty("testlink.url");
 *
 * TestLinkAPIClient client = new TestLinkAPIClient(devkey, url);
 * client.reportTestCaseResult(project, testPlan, ct, build, note, result);
 *
 * TestLinkAPIResults resultado = client.getLastExecutionResult(project,
 * testPlan, ct); String executionId = resultado.getValueByName(0,
 * "id").toString();
 *
 * uploadEvidence(executionId);
 *
 * } catch (Exception exception) { ExceptionUtils.throwException(exception); } }
 *
 *
 * public static void uploadEvidence(String executionId) { try { devkey =
 * Config.getProperty("testlink.devkey"); url =
 * Config.getProperty("testlink.url");
 *
 * URL testlinkURL = new URL(url); TestLinkAPI api = new
 * TestLinkAPI(testlinkURL, devkey);
 *
 * File attachmentFile = new File(Evidencia.getCaminho() + Evidencia.getNome());
 *
 * byte[] byteArray = FileUtils.readFileToByteArray(attachmentFile); String
 * fileContent = new String(Base64.encodeBase64Chunked(byteArray));
 *
 * api.uploadExecutionAttachment( Integer.parseInt(executionId),
 * "Evidência de teste",
 * "Durante a execução os prints foram colocados no documento .pdf em anexo",
 * Evidencia.getNome(), "document/pdf", fileContent ); }catch(Exception
 * exception) { ExceptionUtils.throwException(exception); } }
 *
 * }
 */