package org.codigolibre.auditbpmn.wso2mediator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.apache.axiom.om.OMElement;
import org.apache.commons.io.IOUtils;
import org.apache.synapse.Mediator;
import org.apache.synapse.config.SynapseConfigUtils;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceListener;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.w3c.dom.Node;

@RunWith(Parameterized.class)
public class AuditMediatorSerializationTestCase extends XMLTestCase {

	private String auditXMLProxy;
	private static String fileExtension = ".xml";
	private static String xmls_directory = "./src/main/test/resources/";

	private AuditMediatorFactory auditMediatorFactory;
	private AuditMediatorSerializer auditMediatorSerializer;

	public AuditMediatorSerializationTestCase(String auditXMLProxy) {
		super();
		auditMediatorFactory = new AuditMediatorFactory();
		auditMediatorSerializer = new AuditMediatorSerializer();
		this.auditXMLProxy = auditXMLProxy;
	}

	@BeforeClass
	public static void beforeClass() {
		XMLUnit.setIgnoreWhitespace(true);
		XMLUnit.setIgnoreComments(true);
		XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true);
		XMLUnit.setIgnoreAttributeOrder(true);
		XMLUnit.setNormalizeWhitespace(true);
	}

	// execute only once, in the end
	@AfterClass
	public static void afterClass() {

	}

	// creates the test data
	@Parameters
	public static Collection<Object[]> data() {

		// read xml files in resource test directory

		FileFilter fileFilter = new FileFilter(fileExtension);

		Collection<Object[]> salida = new ArrayList<Object[]>();

		File directorio = new File(xmls_directory);

		String[] ficheros = directorio.list(fileFilter);

		for (int i = 0; i < ficheros.length; i++) {
			String file = ficheros[i];
			FileInputStream inputStream = null;
			try {
				inputStream = new FileInputStream(xmls_directory + ficheros[i]);
				String everything = IOUtils.toString(inputStream);
				salida.add(new String[] { everything });

			} catch (Exception e) {

				System.err.println("Error in file " + file + " "
						+ e.getMessage());
				e.printStackTrace();
			} finally {
				if (inputStream != null)
					try {
						inputStream.close();
					} catch (IOException e) {
						System.err.println("Error in file " + file + " "
								+ e.getMessage());
						e.printStackTrace();
					}
			}
		}

		return salida;
	}

	@Test
	public void testAuditMediatorSerialization() throws Exception {
		beforeClass();
	
		Mediator auditMediator = auditMediatorFactory.createMediator(
				SynapseConfigUtils.stringToOM(auditXMLProxy), new Properties());
		OMElement root = auditMediatorSerializer.serializeMediator(null,
				auditMediator);
		
		//System.out.println(root.toString());

		Diff myDiff = new Diff(auditXMLProxy, root.toString());

		MyDifferenceListener listener = new MyDifferenceListener();
		
		//myDiff.overrideDifferenceListener(listener);

		
		
		assertXMLEqual("Original " + auditXMLProxy + " \n generated " + root.toString(),myDiff,true);

	
	}

	private static class FileFilter implements FilenameFilter {

		private String fileExtension;

		public FileFilter(String fileExtension) {

			this.fileExtension = fileExtension;

		}

		@Override
		public boolean accept(File directory, String fileName) {

			return (fileName.endsWith(this.fileExtension));

		}

	}

	class MyDifferenceListener implements  DifferenceListener {
	    private boolean calledFlag = false;
	    
	   	    
	    public boolean called() { return calledFlag; }



		@Override
		public int differenceFound(Difference diff) {
			
		if (	diff.getControlNodeDetail().getNode().getAttributes().getNamedItem("new").getNodeValue()=="null" &&
				diff.getTestNodeDetail().getNode().getAttributes().getNamedItem("new").getNodeValue().equalsIgnoreCase("new")){

			return this.RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
		}

	        return RETURN_ACCEPT_DIFFERENCE;
		}

		@Override
		public void skippedComparison(Node arg0, Node arg1) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
}
