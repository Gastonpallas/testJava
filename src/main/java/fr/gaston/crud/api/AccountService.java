package fr.gaston.crud.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {


    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {

        return accountRepository.findAll();
    }


    public void ajouterUser(Account account) {

        Optional<Account> accountOptional = accountRepository.findUserByUsername(account.getUsername());
        if (accountOptional.isPresent()) {
            throw new IllegalStateException("nom déjà utilisé");
        }
        accountRepository.save(account);
    }

    public void supprimerUser(Long accountId) {

        boolean accountExists = accountRepository.existsById(accountId);

        if (!accountExists) {
            throw new IllegalStateException("L'id " + accountId + " à supprimer n'existe pas");
        }
        accountRepository.deleteById(accountId);
    }

    @Transactional
    public void modifierAccount(Long accountId, String newUsername) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalStateException(
                        "l'account avec l'id " + accountId + "n'existe pas"
                ));
        if (newUsername != null && newUsername.length() > 0 && !Objects.equals(account.getUsername(), newUsername)) {
            Optional<Account> accountOptional = accountRepository.findUserByUsername(newUsername);
            if (accountOptional.isPresent()) {
                throw new IllegalStateException("nom déjà utilisé");
            }
            account.setUsername(newUsername);
        }
    }

}
