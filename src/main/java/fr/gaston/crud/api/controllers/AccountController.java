package fr.gaston.crud.api.controllers;

import fr.gaston.crud.api.entitys.Account;
import fr.gaston.crud.api.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/crud")
@CrossOrigin
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

    @RequestMapping(method = RequestMethod.GET, params = "nom")
    public Account getAccountByUsername(@RequestParam(required = true) String nom) {
        return accountService.getAccountByUsername(nom);
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
