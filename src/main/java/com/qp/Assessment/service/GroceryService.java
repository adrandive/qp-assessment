package com.qp.Assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qp.Assessment.domain.Grocery;
import com.qp.Assessment.dto.GroceryRequest;
import com.qp.Assessment.repository.GroceryRepository;

@Service
public class GroceryService {

	private GroceryRepository groceryRepository;
	List<Grocery> cartItems;
	
	public Grocery getGrocey(long id) {
		return this.groceryRepository.findByItemId(id);
	}
	
	public GroceryService(GroceryRepository groceryRepository,List<Grocery> cartItems) {
		this.groceryRepository=groceryRepository;
		this.cartItems=cartItems;
	}
	
	public Grocery createItem(String itemName,long itemPrice,long itemQuantity){
		Grocery g=new Grocery();
		g.setItemName(itemName);
		g.setItemPrice(itemPrice);
		g.setItemQuantity(itemQuantity);
		return groceryRepository.save(g);
	}

	public List<Grocery> fetchAllDetails(){
		return groceryRepository.findAll();
	}
	
	public boolean deleteItem(long itemId) {
		Grocery itemToDelete = groceryRepository.findByItemId(itemId);
		groceryRepository.delete(itemToDelete);
		return true;
	}
	
	public boolean updateGrocery(GroceryRequest newGrocery) {
		Grocery itemToUpdate = groceryRepository.findByItemId(newGrocery.getItemId());
		itemToUpdate.setItemName(newGrocery.getItemName());
		itemToUpdate.setItemPrice(newGrocery.getItemPrice());
		itemToUpdate.setItemQuantity(newGrocery.getItemQuantity());
		groceryRepository.save(itemToUpdate);
		return true;
	}
	
	public List<Grocery> getAvailableItems(){
		return groceryRepository.getAvailableGroceries();
	}
	
	public List<Grocery> addToCart(GroceryRequest addItem) {
		boolean flag=false;
		for(Grocery existingItem:cartItems) {
			if(existingItem.getItemId()==addItem.getItemId()) {
				existingItem.setItemQuantity(existingItem.getItemQuantity()+addItem.getItemQuantity());
				flag=true;
				break;
			}
		}
		if(!flag) {
			Grocery g=new Grocery();
			g.setItemId(addItem.getItemId());
			g.setItemName(addItem.getItemName());
			g.setItemPrice(addItem.getItemPrice());
			g.setItemQuantity(addItem.getItemQuantity());
			cartItems.add(g);
		}
		
		Grocery itemToUpdate = groceryRepository.findByItemId(addItem.getItemId());
		long newQuantityValue = itemToUpdate.getItemQuantity()-addItem.getItemQuantity();
		addItem.setItemQuantity(newQuantityValue);
		updateGrocery(addItem);
		return cartItems;
	}
}
