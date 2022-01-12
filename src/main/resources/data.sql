insert into business_constants (description, value, name) values ("Percentage taken on successful reservation.", 15.00, "ptosr");

insert into administrator (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Marko", "Stankovic", "stankovictab@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, 1291623784);
insert into administrator (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Stanko", "Markovic", "tabstankovic@gmail.com", "0", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, -1872771714);
insert into administrator (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Nikola", "Todorčevic", "random1@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, 625244223);

insert into boat_owner (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Damon", "Beasley", "random2@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, -1171707136);
-- random3@gmail.com
insert into boat_owner (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Arden", "Dodge", "todorcevic.marko8@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, 1326308801);
insert into boat_owner (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Jemima", "Fishman", "random4@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, -470642558);
--neodobren
insert into boat_owner (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Doug", "Leon", "random16@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", FALSE, 924836377);


insert into cottage_owner (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Mike", "Oxmaul", "random5@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, 2027373379);
insert into cottage_owner (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Gil", "Sumner", "random6@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, 230422020);
insert into cottage_owner (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Gus", "Fring", "random7@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, -1566529339);
--neodobren
insert into cottage_owner (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Ted", "Osbourne", "random17@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", FALSE, -872114982);


insert into fishing_instructor (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash, start_work_period, end_work_period) values ("Zhon", "China", "random8@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, 931486598, "2023-01-01", "2028-01-15" );
insert into fishing_instructor (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash, start_work_period, end_work_period) values ("Topher", "Craft", "random9@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, -865464761, "2023-01-01", "2028-01-15" );
insert into fishing_instructor (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash, start_work_period, end_work_period) values ("Rickie", "Croft", "random10@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, -1178357357, "2023-01-01", "2028-01-15" );
--neodobren
insert into fishing_instructor (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash, start_work_period, end_work_period) values ("Wendell", "Wade", "random18@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", FALSE, 1625900955, "2023-01-01", "2028-01-15" );

insert into request_for_admin(for_type, user_registration_reason, requester_id) values (1, "Description of a registration request 1.", 4);
insert into request_for_admin(for_type, user_registration_reason, requester_id) values (3, "Description of a registration request 2.", 4);
insert into request_for_admin(for_type, user_registration_reason, requester_id) values (5, "Description of a registration request 3.", 4);

insert into deletion_request(for_type, reason, requester_id, name, lastname) values (1, "I want another account.", 2, "Arden", "Dodge");
insert into deletion_request(for_type, reason, requester_id, name, lastname) values (3, "Just delete it please.", 1, "Clair", "Albertson");

insert into registered_user (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Oliver", "Brooks", "todorcevic.marko.uni@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, 1319658580);
insert into registered_user (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Max", "Morris", "random12@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, -477292779);
insert into registered_user (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Rory", "Atkinson", "random13@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, 2020723158);
insert into registered_user (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Jennifer", "Wells", "random14@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, 223771799);
insert into registered_user (name, lastname, email, password, address, city, country, telephone_number, authenticated, email_hash) values ("Isabel", "Poole", "random15@gmail.com", "testpassword", "42 John Lane", "Johnville", "Canada", "064121213", TRUE, -1573179560);


insert into cottage(name, address, geo_lng, geo_lat, promo_description, rules, price_per_day, price_and_info, reservation_start, reservation_end, max_users, owner_id, hidden) values ("Old Pine Cabin", "Montauk, New York", 41.049690, -71.909219, "Sample Promo Description", "Sample Rules", 130.00, "Speakers:30;Bar:20", "2020-01-01", "2026-01-01", 4, 1, false);
insert into cottage(name, address, geo_lng, geo_lat, promo_description, rules, price_per_day, price_and_info, reservation_start, reservation_end, max_users, owner_id, hidden) values ("Wayward Cottage", "Burlington, Vermont", 44.415146, -73.254865, "Sample Promo Description", "Sample Rules", 185.00, "Speakers:40;Bar:20", "2020-01-01", "2028-01-01", 3, 1, false);
insert into cottage(name, address, geo_lng, geo_lat, promo_description, rules, price_per_day, price_and_info, reservation_start, reservation_end, max_users, owner_id, hidden) values ("Old Fishing Place", "Diamond Lake, Oregon", 43.163765, -122.167166, "Sample Promo Description", "Sample Rules", 90.00, "Speakers:50;Bar:20", "2020-01-01", "2023-01-01", 5, 1, false);
insert into cottage(name, address, geo_lng, geo_lat, promo_description, rules, price_per_day, price_and_info, reservation_start, reservation_end, max_users, owner_id, hidden) values ("Catfish Lounge", "Copper Harbour, Michigan", 47.473378, -87.902598, "Sample Promo Description", "Sample Rules", 150.00, "Speakers:60;Bar:20", "2020-01-01", "2028-01-01", 2, 1, false);
insert into cottage(name, address, geo_lng, geo_lat, promo_description, rules, price_per_day, price_and_info, reservation_start, reservation_end, max_users, owner_id, hidden) values ("Lumberjack's Getaway", "Deer Lake, Newfoundland", 49.139093, -57.552313, "Sample Promo Description", "Sample Rules", 150.00, "Speakers:70;Bar:20", "2020-01-01", "2028-01-01", 4, 1, false);

insert into room(cottage_id, number_of_beds, hidden) values (1, 2, false);
insert into room(cottage_id, number_of_beds, hidden) values (1, 4, false);
insert into room(cottage_id, number_of_beds, hidden) values (2, 2, false);
insert into room(cottage_id, number_of_beds, hidden) values (2, 2, false);
insert into room(cottage_id, number_of_beds, hidden) values (3, 4, false);
insert into room(cottage_id, number_of_beds, hidden) values (4, 6, false);
insert into room(cottage_id, number_of_beds, hidden) values (5, 8, false);

insert into adventure(name, address, geo_lng, geo_lat, promo_description, instructor_id, instructor_bio, equipment, rules, price_and_info, percent_taken_if_cancelled, location, max_users, price_per_day, hidden) values ("Bow Fishing", "Wolfe Island, Ontario", 44.151642, -76.375484, "Sample Promo Description", 1, "Sample bio", "Sample Equipment", "Sample Rules", "Speakers:30;Bar:20", 90, "Sample Location", 2, 100, false);
insert into adventure(name, address, geo_lng, geo_lat, promo_description, instructor_id, instructor_bio, equipment, rules, price_and_info, percent_taken_if_cancelled, location, max_users, price_per_day, hidden) values ("Isolated Fishing", "Bar Harbour, Maine", 44.374710, -68.191748, "Sample Promo Description", 1, "Sample bio", "Sample Equipment", "Sample Rules", "Speakers:30;Bar:20", 90, "Sample Location", 2, 60, false);
insert into adventure(name, address, geo_lng, geo_lat, promo_description, instructor_id, instructor_bio, equipment, rules, price_and_info, percent_taken_if_cancelled, location, max_users, price_per_day, hidden) values ("Ice Fishing", "Arcadia, Alberta", 55.402453, -116.123231, "Sample Promo Description", 1, "Sample bio", "Sample Equipment", "Sample Rules", "Speakers:30;Bar:20", 90, "Sample Location", 2, 85, false);
insert into adventure(name, address, geo_lng, geo_lat, promo_description, instructor_id, instructor_bio, equipment, rules, price_and_info, percent_taken_if_cancelled, location, max_users, price_per_day, hidden) values ("Net Fishing", "Bering Strait", 65.562156, -168.451294, "Sample Promo Description", 1, "Sample bio", "Sample Equipment", "Sample Rules", "Speakers:30;Bar:20", 90, "Sample Location", 2, 70, false);

insert into subscription (for_entity, subbed_id, subber_id) values (0, 1, 1);
insert into subscription (for_entity, subbed_id, subber_id) values (2, 1, 1);
insert into subscription (for_entity, subbed_id, subber_id) values (4, 1, 1);


insert into boat(owner_id ,name, type, boat_length, engine_number, engine_power, max_speed, navigation_equipment, address, geo_lng, geo_lat, promo_description, rules, price_per_day, reservation_start, reservation_end, price_and_info, max_users, hidden, equipment)
            values(1, "Nepotopivi 2", "Yamaha", "15", 1, 321, 321, "Sonar", "Jarkovci, Serbia", 45.043798, 20.027075, "VeryFastBoy", "Don't talk about Nepotopivi 1", 50, "2023-01-01", "2023-01-15", "Speakers:30;Bar:20", 7, false, "stap za pecanje");

insert into boat(owner_id ,name, type, boat_length, engine_number, engine_power, max_speed, navigation_equipment, address, geo_lng, geo_lat, promo_description, rules, price_per_day, reservation_start, reservation_end, price_and_info, max_users, hidden, equipment)
            values(1, "Margo", "Yamaha", "15", 1, 321, 321, "Sonar", "Bar Harbour, Maine", 44.374710, -68.191748, "VeryFastBoy", "Nije lose", 50, "2023-01-01", "2028-01-15", "Speakers:30;Bar:20", 7, false, "stap za pecanje");

insert into boat(owner_id ,name, type, boat_length, engine_number, engine_power, max_speed, navigation_equipment, address, geo_lng, geo_lat, promo_description, rules, price_per_day, reservation_start, reservation_end, price_and_info, max_users, hidden, equipment)
            values(1, "Bay City Jewel", "Yamaha", "15", 1, 321, 321, "Sonar", "Bay City State Park, Michigan", 43.666608, -83.901237, "VeryFastBoy", "Ipak nije tako brz", 50, "2023-01-01", "2028-01-15", "Speakers:30;Bar:20", 7, false, "stap za pecanje");

insert into boat(owner_id ,name, type, boat_length, engine_number, engine_power, max_speed, navigation_equipment, address, geo_lng, geo_lat, promo_description, rules, price_per_day, reservation_start, reservation_end, price_and_info, max_users, hidden, equipment)
            values(1, "Just a Splash", "Yamaha", "15", 1, 321, 321, "Sonar", "Claremont, Virginia", 37.231071, -76.954797, "VeryFastBoy", "Potopio nepotopivog1", 50, "2023-01-01", "2028-01-15", "Speakers:30;Bar:20", 7, false, "stap za pecanje");

insert into boat(owner_id ,name, type, boat_length, engine_number, engine_power, max_speed, navigation_equipment, address, geo_lng, geo_lat, promo_description, rules, price_per_day, reservation_start, reservation_end, price_and_info, max_users, hidden, equipment)
            values(1, "Peti Sveti", "Yamaha", "15", 1, 321, 321, "Sonar", "Key West, Florida", 24.549540, -81.780825, "Holie", "Ovaj jeste Sveti Peti", 50, "2023-01-01", "2028-01-15", "Speakers:30;Bar:20", 7, false, "stap za pecanje");


--rezervacije u prvoj polovini januara, pravice problem u januaru
insert into room_booking (booked_room_id, start, end, registered_user_id, quick_booking, price, max_users, extra_service, cottage_id)
			values (1, "2020-01-01", "2023-01-05", 1, false, 0, 2, "", 1);
insert into room_booking (booked_room_id, start, end, registered_user_id, quick_booking, price, max_users, extra_service, cottage_id)
			values (2, "2020-01-01", "2023-01-05", 2, false, 0, 2, "", 1);
insert into room_booking (booked_room_id, start, end, registered_user_id, quick_booking, price, max_users, extra_service, cottage_id)
			values (1, "2020-01-01", "2023-01-05", 3, false, 0, 2, "", 2);

insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (1, 1, "2020-01-01", "2023-01-02", 0, false, "", 6);
insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (2, 2, "2020-01-01", "2023-01-02", 0, false, "", 6);
insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (3, 3, "2020-01-01", "2023-01-02", 0, false, "", 6);

insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (2, 1, "2023-01-03", "2023-01-04", 0, false, "", 6);

insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (3, 3, "2023-01-05", "2023-01-06", 0, false, "", 6);
-- Akcije
insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (0, 1, "2023-01-05", "2023-01-06", 35, true, "", 6);

insert into adventure_booking(booked_adventure_id, start, end, max_users, price, quick_booking, registered_user_id, extra_service, booked_instructor_id) 
			values(1, "2022-01-12", "2022-02-01", 2, 0, false, 1, "", 1);
insert into adventure_booking(booked_adventure_id, start, end, max_users, price, quick_booking, registered_user_id, extra_service, booked_instructor_id) 
			values(1, "2022-03-01", "2022-04-01", 2, 0, false, 1, "", 1);
insert into adventure_booking(booked_adventure_id, start, end, max_users, price, quick_booking, registered_user_id, extra_service, booked_instructor_id) 
			values(1, "2022-04-15", "2022-05-01", 2, 0, false, 1, "", 1);

insert into adventure_booking(booked_adventure_id, start, end, max_users, price, quick_booking, registered_user_id, extra_service, booked_instructor_id) 
			values(1, "2020-01-01", "2021-01-01", 2, 0, false, 1, "", 1);
insert into adventure_booking(booked_adventure_id, start, end, max_users, price, quick_booking, registered_user_id, extra_service, booked_instructor_id) 
			values(2, "2020-01-01", "2021-01-01", 2, 0, false, 1, "", 1);
-- Akcije
insert into adventure_booking(booked_adventure_id, start, end, max_users, price, quick_booking, registered_user_id, extra_service, booked_instructor_id) 
			values(1, "2022-12-12", "2023-02-01", 2, 70, true, 0, "", 1);

-- Ovo su rezervacije za SOBE, ne za Cottage
-- Rezervacija u proslosti radi prikaza
insert into room_booking (booked_room_id, start, end, registered_user_id, quick_booking, price, max_users, extra_service, cottage_id)
			values (1, "1990-01-01", "1990-01-05", 1, false, 0, 2, "", 1);
insert into room_booking (booked_room_id, start, end, registered_user_id, quick_booking, price, max_users, extra_service, cottage_id)
			values (1, "2021-01-01", "2021-02-01", 1, false, 0, 2, "", 1);
-- Akcije
insert into room_booking (booked_room_id, start, end, registered_user_id, quick_booking, price, max_users, extra_service, cottage_id)
			values (1, "2022-02-02", "2023-02-01", 0, true, 50, 2, "", 1);
insert into room_booking (booked_room_id, start, end, registered_user_id, quick_booking, price, max_users, extra_service, cottage_id)
			values (2, "2023-01-01", "2024-02-01", 0, true, 50, 2, "", 1);
insert into room_booking (booked_room_id, start, end, registered_user_id, quick_booking, price, max_users, extra_service, cottage_id)
			values (3, "2024-01-01", "2025-02-01", 0, true, 70, 2, "", 2);
insert into room_booking (booked_room_id, start, end, registered_user_id, quick_booking, price, max_users, extra_service, cottage_id)
			values (4, "2024-01-01", "2025-02-01", 0, true, 75, 2, "", 2);
            
-- Past Bookings
insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (1, 1, "1990-01-01", "1990-01-02", 0, false, "", 6);

insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (2, 1, "1990-01-03", "1990-01-04", 0, false, "", 6);

insert into boat_booking(registered_user_id, booked_boat_id, start, end, price, quick_booking, extra_service, max_users) 
            values (3, 3, "1990-01-05", "1990-01-06", 0, false, "", 6);

insert into adventure_booking(booked_adventure_id, start, end, max_users, price, quick_booking, registered_user_id, extra_service, booked_instructor_id) 
			values(1, "1990-01-01", "1990-02-01", 2, 0, false, 1, "", 1);

insert into adventure_booking(booked_adventure_id, start, end, max_users, price, quick_booking, registered_user_id, extra_service, booked_instructor_id) 
			values(1, "1990-01-03", "1990-01-04", 2, 0, false, 1, "", 1);

insert into revision(type, booking_id, revised, revision, rating, approved, denied, main_entity_id) 
values (0, 4, true, "Turbulent...", 2.5, false, false, 1);

insert into revision(type, booking_id, revised, revision, rating, approved, denied, main_entity_id) 
values (2, 2, true, "Very comfy!", 4.33333333, false, false, 1);

insert into revision(type, booking_id, revised, revision, rating, approved, denied, main_entity_id) 
values (4, 3, true, "The instructor was very funny.", 5, false, false, 1);

insert into appeal(type, booking_id, appeal) values (0, 4, "There's a big hole in the floor...");

insert into appeal(type, booking_id, appeal) values (2, 2, "Please change the sink.");

insert into appeal(type, booking_id, appeal) values (4, 3, "All the equipment is old.");