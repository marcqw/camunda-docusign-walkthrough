# camunda-docusign-walkthrough



## Get the holy access-token

1. Get a Docusign developer account. You can [create an account](https://go.docusign.com/o/sandbox/?postActivateUrl=https://developers.docusign.com) for free at the [Developer Center](https://developers.docusign.com/).

2. Open the [Apps and Keys](https://admindemo.docusign.com/authenticate?goTo=appsAndKeys) page in your developer account settings

   1. Click on the blue button ***Add App and Integration Key***.
   2. Name your App.
   3. Copy your ***Intégration Key***.
   4. Click on the button ***Add Secret Key*** and copy it.
   5. Click on the button ***Add URI*** and fill with ***https://developers.docusign.com/platform/auth/consent***
   6. In the last section ***Allowed HTTP Methods***, check all the HTTP methods.
   7. Click on the button ***Save***

   **RECAP**: You should have the following variables in hand

   - integrationKey = a909ca71-9918-4f85-9d94-cceed460104c
   - secretKey = d50b9701-30de-4543-97b3-b19178a983ca

3. We assume that you will use the Authorization Code Grant authentication method.

   1. Create authentication URL

      ```
      https://account-d.docusign.com/oauth/auth?response_type=code&scope=impersonation signature&client_id={integrationKey}&redirect_uri=https://developers.docusign.com/platform/auth/consent
      
      ```

      Replace {integrationKey} by your integrationKey variable.

      ```
      https://account-d.docusign.com/oauth/auth?response_type=code&scope=impersonation signature&client_id=a909ca71-9918-4f85-9d94-cceed460104c&redirect_uri=https://developers.docusign.com/platform/auth/consent
      ```

   2. Open your web browser and paste the previous URL.

      1. Allow Access.

      2. Copy the URL of the new page.

         ```
         https://developers.docusign.com/platform/auth/consent/?code=eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQsAAAABAAYABwAAPKeeR5fcSAgAAMgt5keX3EgCAPCGzRoxM-JNnrdx27qmnoQVAAEAAAAYAAIAAAAdAAAABQAAAA0AJAAAAGE5MDljYTcxLTk5MTgtNGY4NS05ZDk0LWNjZWVkNDYwMTA0YyIAJAAAAGE5MDljYTcxLTk5MTgtNGY4NS05ZDk0LWNjZWVkNDYwMTA0YzAAADynnkeX3EgSAAEAAAALAAAAaW50ZXJhY3RpdmU3AGJyVV7vSMZFl1O_ig6IaH8.YXoWrMCGowZ2plaAvgHoa6hnXv82IeGkwRJZq24WdJtDKkM43r_o1bUCU-8MeyA26xIGAY-dqg94-sQKY4vcNcC-xg26UoOeLyV4zbfiyygwJ-HPvk7Gfiy-DvXggcorPLbgNYhoMb4h2zWHo-OtO4GMVg5FeDbqPWiGKsBtzRrs8DxkGYHfSi_vkzE9Ruqe7HiaXwu0g-6aA8cGjTZLKNi35aTluUCq1Tt-ctVL00kSh6P_5FqOGNYoW-R2cN-tK_dqFfQKKLCwK0UjBSY_XziEnMrrkMA9o5AMVaJ8myCuPqUNvisz4UtKWaokoVuvU7-p_uOnSp1upDup8VLVng
         ```

      3. Copy the ***code*** part

         ```
         eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQsAAAABAAYABwAAPKeeR5fcSAgAAMgt5keX3EgCAPCGzRoxM-JNnrdx27qmnoQVAAEAAAAYAAIAAAAdAAAABQAAAA0AJAAAAGE5MDljYTcxLTk5MTgtNGY4NS05ZDk0LWNjZWVkNDYwMTA0YyIAJAAAAGE5MDljYTcxLTk5MTgtNGY4NS05ZDk0LWNjZWVkNDYwMTA0YzAAADynnkeX3EgSAAEAAAALAAAAaW50ZXJhY3RpdmU3AGJyVV7vSMZFl1O_ig6IaH8.YXoWrMCGowZ2plaAvgHoa6hnXv82IeGkwRJZq24WdJtDKkM43r_o1bUCU-8MeyA26xIGAY-dqg94-sQKY4vcNcC-xg26UoOeLyV4zbfiyygwJ-HPvk7Gfiy-DvXggcorPLbgNYhoMb4h2zWHo-OtO4GMVg5FeDbqPWiGKsBtzRrs8DxkGYHfSi_vkzE9Ruqe7HiaXwu0g-6aA8cGjTZLKNi35aTluUCq1Tt-ctVL00kSh6P_5FqOGNYoW-R2cN-tK_dqFfQKKLCwK0UjBSY_XziEnMrrkMA9o5AMVaJ8myCuPqUNvisz4UtKWaokoVuvU7-p_uOnSp1upDup8VLVng
         ```

      **RECAP**: You should have the following variables in hand

      - codeFromUrl = eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQsAAAABAAYABwAAPKeeR5fcSAgAAMgt5keX3EgCAPCGzRoxM-JNnrdx27qmnoQVAAEAAAAYAAIAAAAdAAAABQAAAA0AJAAAAGE5MDljYTcxLTk5MTgtNGY4NS05ZDk0LWNjZWVkNDYwMTA0YyIAJAAAAGE5MDljYTcxLTk5MTgtNGY4NS05ZDk0LWNjZWVkNDYwMTA0YzAAADynnkeX3EgSAAEAAAALAAAAaW50ZXJhY3RpdmU3AGJyVV7vSMZFl1O_ig6IaH8.YXoWrMCGowZ2plaAvgHoa6hnXv82IeGkwRJZq24WdJtDKkM43r_o1bUCU-8MeyA26xIGAY-dqg94-sQKY4vcNcC-xg26UoOeLyV4zbfiyygwJ-HPvk7Gfiy-DvXggcorPLbgNYhoMb4h2zWHo-OtO4GMVg5FeDbqPWiGKsBtzRrs8DxkGYHfSi_vkzE9Ruqe7HiaXwu0g-6aA8cGjTZLKNi35aTluUCq1Tt-ctVL00kSh6P_5FqOGNYoW-R2cN-tK_dqFfQKKLCwK0UjBSY_XziEnMrrkMA9o5AMVaJ8myCuPqUNvisz4UtKWaokoVuvU7-p_uOnSp1upDup8VLVng

   3. Open an empty browser tab and click on the top menu ***View*** > ***Developer*** > ***Developer Tools*** then select the tab ***Console***

      1. Paste the following code into the console 

         ```
         btoa('{iKey}:{secret key}')
         ```

      2. Replace with your varaibles

         ```
         btoa('a909ca71-9918-4f85-9d94-cceed460104c:d50b9701-30de-4543-97b3-b19178a983ca')
         ```

      3. Press ***Enter***

      4. Copy the result.

      **RECAP**: At this point you should have the following variables

      - **integrationKey** = a909ca71-9918-4f85-9d94-cceed460104c
      - **secretKey** = d50b9701-30de-4543-97b3-b19178a983ca
      - **codeFromUrl** = eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQsAAAABAAYABwAAPKeeR5fcSAgAAMgt5keX3EgCAPCGzRoxM-JNnrdx27qmnoQVAAEAAAAYAAIAAAAdAAAABQAAAA0AJAAAAGE5MDljYTcxLTk5MTgtNGY4NS05ZDk0LWNjZWVkNDYwMTA0YyIAJAAAAGE5MDljYTcxLTk5MTgtNGY4NS05ZDk0LWNjZWVkNDYwMTA0YzAAADynnkeX3EgSAAEAAAALAAAAaW50ZXJhY3RpdmU3AGJyVV7vSMZFl1O_ig6IaH8.YXoWrMCGowZ2plaAvgHoa6hnXv82IeGkwRJZq24WdJtDKkM43r_o1bUCU-8MeyA26xIGAY-dqg94-sQKY4vcNcC-xg26UoOeLyV4zbfiyygwJ-HPvk7Gfiy-DvXggcorPLbgNYhoMb4h2zWHo-OtO4GMVg5FeDbqPWiGKsBtzRrs8DxkGYHfSi_vkzE9Ruqe7HiaXwu0g-6aA8cGjTZLKNi35aTluUCq1Tt-ctVL00kSh6P_5FqOGNYoW-R2cN-tK_dqFfQKKLCwK0UjBSY_XziEnMrrkMA9o5AMVaJ8myCuPqUNvisz4UtKWaokoVuvU7-p_uOnSp1upDup8VLVng
      - **encodedKey** = YTkwOWNhNzEtOTkxOC00Zjg1LTlkOTQtY2NlZWQ0NjAxMDRjOmQ1MGI5NzAxLTMwZGUtNDU0My05N2IzLWIxOTE3OGE5ODNjYQ==

   4. Let's cook up the HTTP request

      1. Complete with your variables

         ```bash
         curl --location 'https://account-d.docusign.com/oauth/token' \
         --header '{encodedKey}' \
         --header 'Content-Type: application/x-www-form-urlencoded' \
         --data-urlencode 'code={codeFromUrl}' \
         --data-urlencode 'grant_type=authorization_code'
         ```

         Result:

         ```bash
         curl --location 'https://account-d.docusign.com/oauth/token' \
         --header 'Authorization: Basic NmMxZDQ3N2EtZjYwMy00Nzc1LTllNDMtYjA1MGQxNWRiMTAxOjA4NzgzZGEwLWQ1NzMtNDUxMC1iZTY3LThiYjQ1NzVlYzUyOQ==' \
         --header 'Content-Type: application/x-www-form-urlencoded' \
         --data-urlencode 'code=eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQoAAAABAAYABwCAuiTpP5fcSAgAgEarMECX3EgCAPCGzRoxM-JNnrdx27qmnoQVAAEAAAAYAAIAAAAdAAAABQAAAA0AJAAAADZjMWQ0NzdhLWY2MDMtNDc3NS05ZTQzLWIwNTBkMTVkYjEwMSIAJAAAADZjMWQ0NzdhLWY2MDMtNDc3NS05ZTQzLWIwNTBkMTVkYjEwMTcAYnJVXu9IxkWXU7-KDohofzAAADZAqjqX3Eg.43JHEVCcV8G9ctBr6A6lOAQs7jADgxAT6fsxK1npD_perqSlsjlHhY0lYLDkBPF1k83NX5t0c_hAGEOp2_LM-ERj68NNVlxsxyzX_2vA2s64d8-LPF0xhAyMeh_V6wbdxCDiVGhEgvBYbFOoRsPVGGRq5OLDLd7AEc_kQs1FgGjxKlwb-BtuBM74nVCtFglCoOmAZonwV8yfvTeXHRYcHE5TeuSrY1z5S9zTLID1pLzi13Ejcrq3B3wXcgACKLt-riLaywqcO_6sQgl-CdbtGK5yRAOWnmbiYizPQu3HDs-r9Fe_tCU4gpd5HIK84kFlqNwApZSdMVLvTRNIipiliA' \
         --data-urlencode 'grant_type=authorization_code'
         ```

      2. Execute it in a **Terminal**

      3. **Pray** :pray:

      4. Enjoy this moment

         ```json
         {
             "access_token": "eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQoAAAABAAUABwAAAsYCQJfcSAgAAELpEIOX3EgCAPCGzRoxM-JNnrdx27qmnoQVAAEAAAAYAAIAAAAdAAAABQAAAA0AJAAAADZjMWQ0NzdhLWY2MDMtNDc3NS05ZTQzLWIwNTBkMTVkYjEwMSIAJAAAADZjMWQ0NzdhLWY2MDMtNDc3NS05ZTQzLWIwNTBkMTVkYjEwMTAAADZAqjqX3Eg3AGJyVV7vSMZFl1O_ig6IaH8.m_Nl8eS9jctp2mKzpZAJrcrof6dx1fSbuzsmCJpeE_q3-DWTePjEGvpy1e49AMK9bu_THnM5qUeUt7q8AVJWLEenXvJ3IWci7-t3HsHBlRqnX4PesAisYsOy5UYnMGLaEoSp7F0R_JLxlryFVfnkIZ_A93O2a-cxD5RSrHa_3_Bibwj32hUKKVmEBCZI2cOzrKG2Ae4q7YeY5hpCYnybO1_UsshdMM1JXEPgeT79hQDvvWm-ReUPVFrZ0Kho2S0Q57Ww5QrY2l1CL27GD2IN6OSedPcnZZjU4rhlVFvWWZzKtmMkLqEUb1ovvRHclSnZDTjrWF9vQSdOQ5iBoxXLjQ",
             "token_type": "Bearer",
             "refresh_token": "eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQoAAAABAAgABwAAAsYCQJfcSAgAAIIq-9Ku3EgCAPCGzRoxM-JNnrdx27qmnoQVAAEAAAAYAAIAAAAdAAAABQAAAA0AJAAAADZjMWQ0NzdhLWY2MDMtNDc3NS05ZTQzLWIwNTBkMTVkYjEwMSIAJAAAADZjMWQ0NzdhLWY2MDMtNDc3NS05ZTQzLWIwNTBkMTVkYjEwMTAAADZAqjqX3Eg3AGJyVV7vSMZFl1O_ig6IaH8.Pl5yqmyiS4AbxXiyEuNzXJKEAYwHAjL03wwzSDCoXUapfk1F9AYVWiitivnZ8nyOJBNWIW4OPQ6xv5smuJBtKEjJ7ibXypsL7HC0XgEpchx2wTs1w_cYuJLp2Z5-wuveYb51MQhbeiij481V1yXh4WYhdAthZycwarWmjsIP9-i4gs9IYsdprJgrpjDZYKKTYkpN8x_kaf03Pa9Vd-14tBcWmjurOMhrs1dbdSTPZqeUlbige2i554g8ABnXMFYaketmYEqoGl4BNjKilDfUZ1pWKm7oNHY3b6EUWuny5TCVWJhZT779RdT06jcuQfg9Zg_QbIQfVIhQ8tSOKdWE7g",
             "expires_in": 28800,
             "scope": "impersonation signature"
         }
         ```

      

## Set up your environment

### Build the connector

1. Clone https://github.com/Infosys/camunda-connectors

2. Open or navigate to the folder ./connector-docusign

3. Delete the folder ./src/test

4. Execute 

   ```bash
   mvn clean package
   mvn install
   ```

### Build your client

1. Use https://github.com/marcqw/camunda-spring-demo-boilerplate

2. On the POM.XML add your connector dependency

   ```diff
   +<dependency>
   +		<groupId>com.infosys.camundaconnectors.docusign</groupId>
   +		<artifactId>connector-docusign</artifactId>
   +   <version>0.1.0-SNAPSHOT</version>
   +</dependency>
   ```

3. Create your Cluster Client APi and update src/main/resources/application.properties

4. Execute

   ```bash
   mvn clean package
   ```

5. Run your client

   ```bash
   mvn spring-boot:run
   ```

### Configure your Camunda Platform

1. Add your Docusign Account ID & Secret Key to the connector secret in Console
2. In your Modeler project, upload the template element from connector-docusign/element-templates/docusign-connector.json



Congrats! Your environment is up and running 👊