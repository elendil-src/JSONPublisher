package com.elendil.app.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends javax.servlet.http.HttpServlet {

    private final String JSON_SAMPLE  = "{ \"worksById\": {\n"+
            "        \"9780000001306\": {\n"+
            "            \"NotificationType\": \"03\",\n"+
            "            \"ProductIdentifier\": [\n"+
            "                {\n"+
            "                    \"ProductIDType\": \"03\",\n"+
            "                    \"IDValue\": \"9780000001306\"\n"+
            "                },\n"+
            "                {\n"+
            "                    \"ProductIDType\": \"15\",\n"+
            "                    \"IDValue\": \"9780000001306\"\n"+
            "                }\n"+
            "            ],\n"+
            "            \"Barcode\": \"03\",\n"+
            "            \"ProductForm\": \"BC\",\n"+
            "            \"Title\": {\n"+
            "                \"TitleType\": \"01\",\n"+
            "                \"TitleText\": \"CAM Testing: BJ Migration patch 1 2011...\"\n"+
            "            },\n"+
            "            \"Contributor\": {\n"+
            "                \"ContributorRole\": \"A01\",\n"+
            "                \"NamesBeforeKey\": \"James\",\n"+
            "                \"KeyNames\": \"Carne\",\n"+
            "                \"LettersAfterNames\": \"Phd, Msc, MBE\",\n"+
            "                \"ProfessionalAffiliation\": {\n"+
            "                    \"Affiliation\": \"The Elsevier University of Tech, MBA,MCA, professor of Physics\"\n"+
            "                }\n"+
            "            },\n"+
            "            \"Language\": {\n"+
            "                \"LanguageRole\": \"01\",\n"+
            "                \"LanguageCode\": \"eng\"\n"+
            "            },\n"+
            "            \"AudienceCode\": \"06\",\n"+
            "            \"Imprint\": {\n"+
            "                \"NameCodeType\": \"02\",\n"+
            "                \"NameCodeValue\": \"5\",\n"+
            "                \"ImprintName\": \"Academic Press\"\n"+
            "            },\n"+
            "            \"Publisher\": {\n"+
            "                \"PublisherName\": \"Elsevier Science\"\n"+
            "            },\n"+
            "            \"CountryOfPublication\": \"GB\",\n"+
            "            \"PublishingStatus\": \"09\",\n"+
            "            \"PublicationDate\": \"20201215\",\n"+
            "            \"SalesRights\": {\n"+
            "                \"SalesRightsType\": \"01\",\n"+
            "                \"RightsTerritory\": \"WORLD\"\n"+
            "            },\n"+
            "            \"SupplyDetail\": {\n"+
            "                \"SupplierName\": \"Elsevier Science\",\n"+
            "                \"AvailabilityCode\": \"IP\",\n"+
            "                \"ProductAvailability\": \"99\",\n"+
            "                \"ExpectedShipDate\": \"20201222\",\n"+
            "                \"PackQuantity\": \"24\"\n"+
            "            },\n"+
            "            \"PpmData\": {\n"+
            "                \"PMC\": \"300 - Physical and Theoretical Chemistry\",\n"+
            "                \"ExportToOBS\": \"N\",\n"+
            "                \"ExportToWEB\": \"N\",\n"+
            "                \"NotAvailForSale\": \"N\",\n"+
            "                \"NonSalableAncillary\": \"N\",\n"+
            "                \"ContractOrigin\": \"UK\",\n"+
            "                \"Cover\": \"Book/Paperback\",\n"+
            "                \"SeriesCode\": {},\n"+
            "                \"SeriesTitle\": \"CAM testing : to check MP1 2013-\"\n"+
            "            },\n"+
            "            \"chapters\": [\n"+
            "                \"null\"\n"+
            "            ]\n"+
            "        } }";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
//        resp.getWriter().println( "{message: \"json from controller\" }" );
        resp.getWriter().println( JSON_SAMPLE );
    }
}
