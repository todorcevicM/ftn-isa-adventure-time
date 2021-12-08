insert into business_constants (description, value, name) values ("Percentage taken on successful reservation.", 15.00, "ptosr");

insert into administrator (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("Marko", "Stankovic", "stankovictab@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, 1291623784);
insert into administrator (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("Stanko", "Markovic", "tabstankovic@gmail.com", "0", "Address", "City", "Country", "064121213", FALSE, TRUE, -1872771714);
insert into administrator (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("Nikola", "Todorčevic", "random1@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, 625244223);

insert into boat_owner (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("Brodovlasnik1", "Prvi", "random2@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, -1171707136);
insert into boat_owner (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("Brodovlasnik2", "Drugi", "random3@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, 1326308801);
insert into boat_owner (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("Brodovlasnik3", "Treci", "random4@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, -470642558);
--neodobren
insert into boat_owner (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("Brodovlasnik4", "Cetvrti", "random16@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, FALSE, 924836377);




insert into cottage_owner (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("Vikendicovlasnik1", "Prvi", "random5@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, 2027373379);
insert into cottage_owner (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("Vikendicovlasnik2", "Drugi", "random6@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, 230422020);
insert into cottage_owner (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("Vikendicovlasnik3", "Treci", "random7@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, -1566529339);
--neodobren
insert into cottage_owner (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("Vikendicovlasnik4", "Cetvrti", "random17@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, FALSE, -872114982);


insert into fishing_instructor (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash, start_work_period, end_work_period) values ("Pecaros1", "Prvi", "random8@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, 931486598, "2023-01-01", "2023-01-15" );
insert into fishing_instructor (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash, start_work_period, end_work_period) values ("Pecaros2", "Drugi", "random9@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, -865464761, "2023-01-01", "2023-01-15" );
insert into fishing_instructor (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash, start_work_period, end_work_period) values ("Pecaros3", "Treci", "random10@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, -1178357357, "2023-01-01", "2023-01-15" );
--neodobren
insert into fishing_instructor (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash, start_work_period, end_work_period) values ("Pecaros4", "Cetvrti", "random18@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, FALSE, 1625900955, "2023-01-01", "2023-01-15" );

insert into request_for_admin(for_type, user_registration_reason, requester_id) values (1, "UwU pwease xDDD, sucky sucky ten bucky", 4);
insert into request_for_admin(for_type, user_registration_reason, requester_id) values (3, "UwO pwease DDxxxDDD, sucky sucky ten bucky", 4);
insert into request_for_admin(for_type, user_registration_reason, requester_id) values (5, "OwO pwease MMMDDD, sucky sucky ten bucky", 4);

insert into registered_user (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("KorisnikUsluga1", "Prvi", "random11@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, 1319658580);
insert into registered_user (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("KorisnikUsluga2", "Drugi", "random12@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, -477292779);
insert into registered_user (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("KorisnikUsluga3", "Treci", "random13@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, 2020723158);
insert into registered_user (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("KorisnikUsluga4", "Cetvrsti", "random14@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, 223771799);
insert into registered_user (name, lastname, email, password, address, city, country, telephone_number, wants_deletion, authenticated, email_hash) values ("KorisnikUsluga5", "Peti", "random15@gmail.com", "testpassword", "Address", "City", "Country", "064121213", FALSE, TRUE, -1573179560);

insert into cottage(name, address, geo_lng, geo_lat, promo_description, rules, price_per_day, price_and_info, reservation_start, reservation_end, max_users, owner_id) values ("Old Pine Cabin", "Montauk, New York", 41.049690, -71.909219, "Sample Promo Description", "Sample Rules", 130.00, "Sample Price and Info", "2020-01-01", "2023-01-01", 4, 1);
insert into cottage(name, address, geo_lng, geo_lat, promo_description, rules, price_per_day, price_and_info, reservation_start, reservation_end, max_users, owner_id) values ("Wayward Cottage", "Burlington, Vermont", 44.415146, -73.254865, "Sample Promo Description", "Sample Rules", 185.00, "Sample Price and Info", "2020-01-01", "2023-01-01", 3, 1);
insert into cottage(name, address, geo_lng, geo_lat, promo_description, rules, price_per_day, price_and_info, reservation_start, reservation_end, max_users, owner_id) values ("Old Fishing Place", "Diamond Lake, Oregon", 43.163765, -122.167166, "Sample Promo Description", "Sample Rules", 90.00, "Sample Price and Info", "2020-01-01", "2023-01-01", 5, 1);
insert into cottage(name, address, geo_lng, geo_lat, promo_description, rules, price_per_day, price_and_info, reservation_start, reservation_end, max_users, owner_id) values ("Catfish Lounge", "Copper Harbour, Michigan", 47.473378, -87.902598, "Sample Promo Description", "Sample Rules", 150.00, "Sample Price and Info", "2020-01-01", "2023-01-01", 2, 1);
insert into cottage(name, address, geo_lng, geo_lat, promo_description, rules, price_per_day, price_and_info, reservation_start, reservation_end, max_users, owner_id) values ("Lumberjack's Getaway", "Deer Lake, Newfoundland", 49.139093, -57.552313, "Sample Promo Description", "Sample Rules", 150.00, "Sample Price and Info", "2020-01-01", "2023-01-01", 4, 1);

insert into room(cottage_id, number_of_beds) values (1, 2);
insert into room(cottage_id, number_of_beds) values (1, 4);
insert into room(cottage_id, number_of_beds) values (2, 2);
insert into room(cottage_id, number_of_beds) values (2, 2);
insert into room(cottage_id, number_of_beds) values (3, 4);
insert into room(cottage_id, number_of_beds) values (4, 6);
insert into room(cottage_id, number_of_beds) values (5, 8);

insert into adventure(name, address, geo_lng, geo_lat, promo_description, instructor_id, instructor_bio, equipment, rules, price_and_info, percent_taken_if_cancelled, location, max_users, price_per_day) values ("Bow Fishing", "Wolfe Island, Ontario", 44.151642, -76.375484, "Sample Promo Description", 1, "Sample bio", "Sample Equipment", "Sample Rules", "Sample Price and Info", 90, "Sample Location", 2, 100);
insert into adventure(name, address, geo_lng, geo_lat, promo_description, instructor_id, instructor_bio, equipment, rules, price_and_info, percent_taken_if_cancelled, location, max_users, price_per_day) values ("Isolated Fishing", "Bar Harbour, Maine", 44.374710, -68.191748, "Sample Promo Description", 1, "Sample bio", "Sample Equipment", "Sample Rules", "Sample Price and Info", 90, "Sample Location", 2, 60);
insert into adventure(name, address, geo_lng, geo_lat, promo_description, instructor_id, instructor_bio, equipment, rules, price_and_info, percent_taken_if_cancelled, location, max_users, price_per_day) values ("Ice Fishing", "Arcadia, Alberta", 55.402453, -116.123231, "Sample Promo Description", 1, "Sample bio", "Sample Equipment", "Sample Rules", "Sample Price and Info", 90, "Sample Location", 2, 85);
insert into adventure(name, address, geo_lng, geo_lat, promo_description, instructor_id, instructor_bio, equipment, rules, price_and_info, percent_taken_if_cancelled, location, max_users, price_per_day) values ("Net Fishing", "Bering Strait", 65.562156, -168.451294, "Sample Promo Description", 1, "Sample bio", "Sample Equipment", "Sample Rules", "Sample Price and Info", 90, "Sample Location", 2, 70);

insert into subscription (for_entity, subbed_id, subber_id) values (1, 1, 1);
insert into subscription (for_entity, subbed_id, subber_id) values (3, 1, 1);
insert into subscription (for_entity, subbed_id, subber_id) values (5, 1, 1);  

insert into boat(owner_id ,name, type, length, engine_number, engine_power, max_speed, navigation_equipment, address, geo_lng, geo_lat, promo_description, rules, price_per_day, reservation_start, reservation_end, price_and_info, max_users)
            values(1, "Nepotopivi 2", "JoMama", "15", 123456, 321, 321, "Sonar", "Address", 44.374710, -68.191748, "VeryFastBoy", "Don't talk about Nepotopivi 1", 50, "2023-01-01", "2023-01-15", "Lorem", 7);

insert into boat(owner_id ,name, type, length, engine_number, engine_power, max_speed, navigation_equipment, address, geo_lng, geo_lat, promo_description, rules, price_per_day, reservation_start, reservation_end, price_and_info, max_users)
            values(1, "Miss Melanoma", "JoMama", "15", 123456, 321, 321, "Sonar", "Address", 44.374710, -68.191748, "VeryFastBoy", "Nije lose", 50, "2023-01-01", "2023-01-15", "Lorem", 7);

insert into boat(owner_id ,name, type, length, engine_number, engine_power, max_speed, navigation_equipment, address, geo_lng, geo_lat, promo_description, rules, price_per_day, reservation_start, reservation_end, price_and_info, max_users)
            values(1, "Brzi", "JoMama", "15", 123456, 321, 321, "Sonar", "Address", 44.374710, -68.191748, "VeryFastBoy", "Ipak nije tako brz", 50, "2023-01-01", "2023-01-15", "Lorem", 7);

insert into boat(owner_id ,name, type, length, engine_number, engine_power, max_speed, navigation_equipment, address, geo_lng, geo_lat, promo_description, rules, price_per_day, reservation_start, reservation_end, price_and_info, max_users)
            values(1, "Just A Splash", "JoMama", "15", 123456, 321, 321, "Sonar", "Address", 44.374710, -68.191748, "VeryFastBoy", "Potopio nepotopivog1", 50, "2023-01-01", "2023-01-15", "Lorem", 7);

insert into boat(owner_id ,name, type, length, engine_number, engine_power, max_speed, navigation_equipment, address, geo_lng, geo_lat, promo_description, rules, price_per_day, reservation_start, reservation_end, price_and_info, max_users)
            values(1, "Peti Sveti", "JoMama", "15", 123456, 321, 321, "Sonar", "Address", 44.374710, -68.191748, "VeryFastBoy", "Potopio nepotopivog1", 50, "2023-01-01", "2023-01-15", "Lorem", 7);


--rezervacije u prvoj polovini januara, pravice problem u januaru
insert into room_booking (booked_room_id, start, end, registered_user_id, quick_booking, price, max_users, extra_service, cottage_id)
			values (1, "2023-01-01", "2023-01-01", 1, false, 100, 2, "", 1);

insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (1, 1, "2023-01-01", "2023-01-02", 100, false, "", 6);

insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (1, 1, "2023-01-03", "2023-01-04", 100, false, "", 6);

insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (1, 1, "2023-01-05", "2023-01-06", 100, false, "", 6);

insert into adventure_booking(booked_adventure_id, start, end, max_users, price, quick_booking, registered_user_id, extra_service, booked_instructor_id) 
			values(1, "2023-01-01", "2023-02-01", 2, 100, false, 1, "", 1);

insert into adventure_booking(booked_adventure_id, start, end, max_users, price, quick_booking, registered_user_id, extra_service, booked_instructor_id) 
			values(1, "2023-01-03", "2023-01-04", 2, 100, false, 1, "", 1);
--Rezervacija u proslosti radi prikaza
insert into room_booking (booked_room_id, start, end, registered_user_id, quick_booking, price, max_users, extra_service, cottage_id)
			values (1, "1990-01-01", "1990-01-01", 1, false, 100, 2, "", 1);
            
insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (1, 1, "1990-01-01", "1990-01-02", 100, false, "", 6);

insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (1, 1, "1990-01-03", "1990-01-04", 100, false, "", 6);

insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (1, 1, "1990-01-05", "1990-01-06", 100, false, "", 6);

insert into adventure_booking(booked_adventure_id, start, end, max_users, price, quick_booking, registered_user_id, extra_service, booked_instructor_id) 
			values(1, "1990-01-01", "1990-02-01", 2, 100, false, 1, "", 1);

insert into adventure_booking(booked_adventure_id, start, end, max_users, price, quick_booking, registered_user_id, extra_service, booked_instructor_id) 
			values(1, "1990-01-03", "1990-01-04", 2, 100, false, 1, "", 1);
