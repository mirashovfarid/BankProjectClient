package az.orient.client.service.impl;

import az.orient.client.service.AccountService;
import az.orient.client.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:config.properties")
public class AccountServiceImpl implements AccountService {

    private final Utility utility;

    @Value("${api.url}")
    private String apiUrl;

    @Override
    public String getAccountList(Long customerId) {

        String url = apiUrl + "account/GetAccountListByCustomerId/" + customerId;
        String result = utility.sendGet(url);

        return result;
    }
}
