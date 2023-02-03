create table mtc_match (id bigint not null auto_increment, away_team_points integer, changed datetime(6), created datetime(6), field varchar(255), finished bit, home_tea_points integer, away_tea_id bigint, home_tea_id bigint, rou_id bigint, primary key (id)) engine=InnoDB;
create table rou_round (id bigint not null auto_increment, changed datetime(6), created datetime(6), finished bit, matching_algorithm varchar(255), number integer, trn_id bigint, primary key (id)) engine=InnoDB;
create table tea_team (id bigint not null auto_increment, changed datetime(6), created datetime(6), goal_difference integer, losses integer, name varchar(255), pool char(1), seed integer, points double precision, wins integer, trn_id bigint, primary key (id)) engine=InnoDB;
create table trn_tournament (id bigint not null auto_increment, changed datetime(6), created datetime(6), current_rnd integer, name varchar(255), primary key (id)) engine=InnoDB;
alter table mtc_match add constraint FKqpv944alj60hat68lsdq658vn foreign key (away_tea_id) references tea_team (id);
alter table mtc_match add constraint FKr4hdf6l7u7cdbnvyq5pgjse9l foreign key (home_tea_id) references tea_team (id);
alter table mtc_match add constraint FKe28w6tftbxbcgg3qcd2qj94yu foreign key (rou_id) references rou_round (id);
alter table rou_round add constraint FKsj32no691vmay7aru0fma786c foreign key (trn_id) references trn_tournament (id);
alter table tea_team add constraint FKlknp64s1l99fvpuyb96qj7rvv foreign key (trn_id) references trn_tournament (id);
