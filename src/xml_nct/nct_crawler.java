/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_nct;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author HaiPT
 */
public class nct_crawler {

    static String song_nct, lyric_nct, time_nct, mp3_nct, avatar_nct, singer_nct;

    /**
     * Save .JPG file as avatar
     */
    public static void saveAvatar(URL url) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(url);
            ImageIO.write(image, "jpg", new File("//avatar.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done");
    }

    /**
     * Save XML file in local
     */
    public static void saveXML(URL url) throws IOException {

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));
        File f3 = new File("F:\\xml_nct.txt");
        FileWriter writer = new FileWriter(f3);
        String inputLine;
        String t = "";
        while ((inputLine = in.readLine()) != null) {
            writer.write(inputLine);
            writer.flush();
        }
        writer.close();
        in.close();
        
//       URL url2 = new URL("http://avatar.nct.nixcdn.com/singer/avatar/2016/05/26/1/0/c/5/1464259325938.jpg");
//    	 saveAvatar(url2);
    }

    /**
     * Read XML file
     */
    public static void readXML() {
        try {
            File inputFile = new File("F:\\xml_nct.txt");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("track");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :"
                        + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    System.out.println("Song : "
                            + eElement
                                    .getElementsByTagName("title")
                                    .item(0)
                                    .getTextContent());

                    System.out.println("Time : "
                            + eElement
                                    .getElementsByTagName("time")
                                    .item(0)
                                    .getTextContent());
                    System.out.println("Singer : "
                            + eElement
                                    .getElementsByTagName("creator")
                                    .item(0)
                                    .getTextContent());
                    System.out.println("Avatar : "
                            + eElement
                                    .getElementsByTagName("avatar")
                                    .item(0)
                                    .getTextContent());
                    System.out.println("Lyric : "
                            + eElement
                                    .getElementsByTagName("lyric")
                                    .item(0)
                                    .getTextContent());
                    System.out.println("Download MP3: "
                            + eElement
                                    .getElementsByTagName("location")
                                    .item(0)
                                    .getTextContent());

                    song_nct
                            = eElement
                                    .getElementsByTagName("title")
                                    .item(0)
                                    .getTextContent();

                    time_nct
                            = eElement
                                    .getElementsByTagName("time")
                                    .item(0)
                                    .getTextContent();
                    singer_nct
                            = eElement
                                    .getElementsByTagName("creator")
                                    .item(0)
                                    .getTextContent();
                    avatar_nct
                            = eElement
                                    .getElementsByTagName("avatar")
                                    .item(0)
                                    .getTextContent();
                    lyric_nct
                            = eElement
                                    .getElementsByTagName("lyric")
                                    .item(0)
                                    .getTextContent();

                    mp3_nct = eElement
                            .getElementsByTagName("location")
                            .item(0)
                            .getTextContent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
