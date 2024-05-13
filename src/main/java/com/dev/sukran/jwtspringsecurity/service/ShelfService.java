package com.dev.sukran.jwtspringsecurity.service;
import com.dev.sukran.jwtspringsecurity.entities.Product;
import com.dev.sukran.jwtspringsecurity.entities.Shelf;
import com.dev.sukran.jwtspringsecurity.repositories.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.management.AttributeNotFoundException;
import java.util.List;



@Service
public class ShelfService {

    private final ShelfRepository shelfRepository;

    @Autowired
    public ShelfService(ShelfRepository shelfRepository) {
        this.shelfRepository = shelfRepository;
    }

    public List<Shelf> getAllShelf() {
        return shelfRepository.findAll();
    }

    public Shelf createShelf(Shelf newShelf) {
        if (newShelf.getShelfNumber() == 0) {
            newShelf.setShelfNumber(1);
        }

        // Initialize other required properties

        return shelfRepository.save(newShelf);
    }

    //BOŞ raf bulma
    public Shelf findEmptyShelf() {
        List<Shelf> shelves = shelfRepository.findAll();
        for (Shelf shelf : shelves) {
            if (shelf.getAvailableSpace() != null && shelf.getAvailableSpace() > 0) {
                return shelf;
            }
        }
        return null;
    }

    public void updateShelf(Shelf updateShelf) {
        shelfRepository.save(updateShelf);
    }

    public void deleteShelf(Long shelfId) {
        shelfRepository.deleteById(shelfId);
    }

    public void placeProductOnShelf(Product product) throws AttributeNotFoundException {
        Shelf shelf = findOrCreateShelfWithSpace();
        int availableSpace = shelf.getAvailableSpace();
        if (availableSpace > 0) {
            // Raftaki mevcut ürün sayısını hesaplıyoruz
            int currentProductsCount = shelf.getProducts().size();

            // Kalan kullanılabilir alanı hesaplıyoruz
            int remainingSpace = shelf.getCapacity() - currentProductsCount;

            if (remainingSpace > 0) {
                // Kullanılabilir alanı 1 azalt
                shelf.setAvailableSpace(remainingSpace - 1);
                product.setShelf(shelf);
                shelfRepository.save(shelf);
            } else {
                // Mevcut rafta yer yoksa yeni bir raf oluştur
                Shelf newShelf = new Shelf();
                newShelf.setCapacity(5);
                newShelf.setAvailableSpace(newShelf.getCapacity() - 1); // Initialize available space
                shelfRepository.save(newShelf);
                // Ürünü yeni oluşturulan rafa yerleştirin
                product.setShelf(newShelf);
            }
        } else {
            // Kullanılabilir alan bulunmayan durumu ele alın
            throw new AttributeNotFoundException("No available space on any shelf.");
        }
    }

    private Shelf findOrCreateShelfWithSpace() {
        List<Shelf> shelves = shelfRepository.findAll();
        for (Shelf shelf : shelves) {
            Integer availableSpace = shelf.getAvailableSpace();
            if (availableSpace != null && availableSpace > 0) {
                return shelf;
            }
        }

        // Kullanılabilir alana sahip raf bulunamazsa yeni bir raf oluşturun
        Shelf newShelf = new Shelf();
        newShelf.setCapacity(5);
        newShelf.setAvailableSpace(newShelf.getCapacity() - 1); // Kullanılabilir alanı başlat
        return shelfRepository.save(newShelf);
    }
}