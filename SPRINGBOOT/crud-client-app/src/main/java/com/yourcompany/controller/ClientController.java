import com.yourcompany.model.Client;
import com.yourcompany.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> find_all() {
        List<Client> clients = clientService.find_all();
        return clients.stream()
                .filter(client -> client.getName().contains("A"))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Client find_by_id(@PathVariable Long id) {
        return clientService.find_by_id(id);
    }

    @PostMapping
    public Client save(@RequestBody Client client) {
        return clientService.save(client);
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }
}
