package com.example.dio.service.Impl;


import com.example.dio.dto.response.BillResponse;
import com.example.dio.dto.response.FoodOrderResponse;
import com.example.dio.enums.OrderStatus;
import com.example.dio.mapper.BillMapper;
import com.example.dio.mapper.FoodOrderMapper;
import com.example.dio.model.Bill;
import com.example.dio.model.FoodOrder;
import com.example.dio.model.Restaurant;
import com.example.dio.model.RestaurantTable;
import com.example.dio.repository.BillRepository;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.FoodOrderRepository;
import com.example.dio.repository.TableRepository;
import com.example.dio.service.BillService;
import com.example.dio.service.PdfService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService {


    private final FoodItemRepository foodItemRepository;
    private final FoodOrderRepository foodOrderRepository;
    private final BillRepository billRepository;
    private final TableRepository tableRepository;
    private final BillMapper billMapper;
    private final FoodOrderMapper foodOrderMapper;
    private final PdfService pdfService;


        @Transactional
        @Override
        public BillResponse createBill(Long tableId) {
            RestaurantTable table=tableRepository.findById(tableId)
                    .orElseThrow(()-> new RuntimeException("Table not found with tableId : "+tableId));

            List<FoodOrder> orders=foodOrderRepository.findByOrderStatusAndRestaurantTable_TableId(OrderStatus.UN_BUILD,tableId);

            Bill bill=new Bill();
            bill.setOrders(orders);

            double totalPayableAmount = calculateTotalPayableAmount(orders);
            bill.setTotalPayableAmount(totalPayableAmount);
            billRepository.save(bill);

            List<Long> foodOrderIds = getOrderIds(orders);

            mapOrdersListToOrderResponses(orders);

            foodOrderRepository.updateFoodOrderOrderStatus(foodOrderIds, OrderStatus.BUILD);


            return billMapper.mapToBillResponse(bill);

        }

        @Override
        public BillResponse findByBillId(Long billId) {
            return billRepository.findById(billId)
                    .map(billMapper::mapToBillResponse)
                    .orElseThrow(()->new RuntimeException("Failed to find bill, Bill not found by id"));

        }

    @Override
    public byte[] generatePdf(Long billId) throws IOException {
        BillResponse billResponse = findByBillId(billId);

        long foodItemId = billResponse.getOrders().getFirst().getCartItems().getFirst().getFoodItem().getItemId();
        long tableNo = billResponse.getOrders().getFirst().getRestaurantTable().getTableNo();

        Restaurant restaurant = foodItemRepository.findRestaurant_RestaurantNameByItemId(foodItemId);
        String restaurantName = restaurant != null ? restaurant.getRestaurantName() : "";

        Map<String, Object> data = Map.of("restaurantName", restaurantName, "bill", billResponse, "tableNo", tableNo, "foodItemId", foodItemId);
        byte[] pdfBytes = pdfService.generatePdf("bill_view", data);

        return pdfBytes;
    }

        private void mapOrdersListToOrderResponses(List<FoodOrder> orders) {
            List<FoodOrderResponse> orderResponses= orders.stream()
                    .map( foodOrderMapper::mapToFoodOrder)
                    .toList();
        }

        private static List<Long> getOrderIds(List<FoodOrder> orders) {
            List<Long> orderIds= orders.stream()
                    .map(FoodOrder::getOrderId)
                    .toList();
            return orderIds;
        }

        public double calculateTotalPayableAmount(List<FoodOrder> orders){
            double totalPayableAmount=0;
            for(FoodOrder order:orders){
                totalPayableAmount=totalPayableAmount+order.getTotalAmount();

            }
            return totalPayableAmount;
        }
    }





