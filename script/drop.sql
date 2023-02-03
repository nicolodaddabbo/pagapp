alter table mtc_match drop foreign key FKqpv944alj60hat68lsdq658vn;
alter table mtc_match drop foreign key FKr4hdf6l7u7cdbnvyq5pgjse9l;
alter table mtc_match drop foreign key FKe28w6tftbxbcgg3qcd2qj94yu;
alter table rou_round drop foreign key FKsj32no691vmay7aru0fma786c;
alter table tea_team drop foreign key FKlknp64s1l99fvpuyb96qj7rvv;
drop table if exists mtc_match;
drop table if exists rou_round;
drop table if exists tea_team;
drop table if exists trn_tournament;
