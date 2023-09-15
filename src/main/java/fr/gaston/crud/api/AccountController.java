package fr.gaston.crud.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/crud")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3000"})
public class AccountController {

    private final AccountService accountService;


    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping
    public void enregistrerUser(@RequestBody Account account) {
        accountService.ajouterUser(account);
    }

    @PutMapping(path = "{accountId}")
    public void modifierUser(@PathVariable("accountId") Long accountId,
                             @RequestParam(required = true) String nom
    ) {
        accountService.modifierAccount(accountId, nom);

    }


    @DeleteMapping(path = "{accountId}")
    public void supprimerUser(@PathVariable("accountId") Long accountId) {
        accountService.supprimerUser(accountId);

    }

}
