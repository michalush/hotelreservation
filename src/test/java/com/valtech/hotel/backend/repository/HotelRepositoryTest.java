package com.valtech.hotel.backend.repository;

//@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE)
//@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
//@Ignore
//public class HotelRepositoryTest extends ESIntegTestCase {
//
//    @Test
//    public void addItem_shouldIndex() throws Exception {
//        Client client = client();
//
//         HotelRepository hotelRepository = new HotelRepository(client);
//        final Hotel hotel = new Hotel();
//        hotel.setId("1");
//
//        hotelRepository.add(hotel);
//
//        refresh(); // otherwise we would not find beers yet
//
//        indexExists("hotels"); // verifies that index 'drinks' exists
//        ensureGreen("hotels"); // ensures cluster status is green
//    }
//}