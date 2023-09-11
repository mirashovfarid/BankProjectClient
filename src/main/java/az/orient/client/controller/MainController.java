package az.orient.client.controller;

import az.orient.client.service.AccountService;
import az.orient.client.service.CustomerService;
import az.orient.client.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final Utility utility;

    private final AccountService accountService;

    private final CustomerService customerService;

    @GetMapping("/getCustomerList")
    public ModelAndView getCustomerList(){
//        ModelAndView model = new ModelAndView();
        String result = customerService.getCustomerList();
        System.out.println(result);
        return null;
    }

    @GetMapping("/GetAccountList/{customerId}")
    public void getAccountList(@PathVariable Long customerId){
        String result = accountService.getAccountList(customerId);
        System.out.println(result);
    }

}
