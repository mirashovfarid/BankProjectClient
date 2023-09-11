package az.orient.client.service.impl;

import az.orient.client.dto.request.ReqLogin;
import az.orient.client.dto.response.Response;
import az.orient.client.service.CustomerService;
import az.orient.client.service.UserService;
import az.orient.client.util.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:config.properties")
public class CustomerServiceImpl implements CustomerService {

    private final Utility utility;

    private final UserService userService;

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.usr}")
    private String apiUsr;

    @Value("${api.pwd}")
    private String apiPwd;


    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String getCustomerList() {
        try {
            ReqLogin reqLogin = new ReqLogin();
            reqLogin.setUsername(apiUsr);
            reqLogin.setPassword(apiPwd);

            String reqLoginJson = objectMapper.writeValueAsString(reqLogin);
            String result = utility.sendPost(apiUrl + "user/login", reqLoginJson);
            Response response = objectMapper.readValue(result, Response.class);
            if (response.getStatus().getCode() != 1){
                return response.getStatus().getMessage();
            }
            String reqTokenJson = objectMapper.writeValueAsString(response.getRespUser().getRespToken());

            String customerList = utility.sendPost(apiUrl + "customer/getCustomerList", reqTokenJson);
            System.out.println(customerList);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
