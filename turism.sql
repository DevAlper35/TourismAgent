PGDMP                 
        |            turism    13.13    13.13 "    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16445    turism    DATABASE     c   CREATE DATABASE turism WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Turkish_Turkey.1254';
    DROP DATABASE turism;
                postgres    false            �            1259    16446    hotel    TABLE     �  CREATE TABLE public.hotel (
    id bigint NOT NULL,
    name character varying NOT NULL,
    address character varying NOT NULL,
    mail character varying NOT NULL,
    phone character varying NOT NULL,
    star character varying,
    car_park boolean NOT NULL,
    wifi boolean NOT NULL,
    pool boolean NOT NULL,
    fitness boolean NOT NULL,
    concierge boolean NOT NULL,
    spa boolean NOT NULL,
    room_service boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16452    hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    200            �            1259    16454    hotel_pension    TABLE     �   CREATE TABLE public.hotel_pension (
    id bigint NOT NULL,
    hotel_id integer NOT NULL,
    pension_type character varying NOT NULL
);
 !   DROP TABLE public.hotel_pension;
       public         heap    postgres    false            �            1259    16460    hotel_pension_id_seq    SEQUENCE     �   ALTER TABLE public.hotel_pension ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    202            �            1259    16462    hotel_season    TABLE     �   CREATE TABLE public.hotel_season (
    id bigint NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
     DROP TABLE public.hotel_season;
       public         heap    postgres    false            �            1259    16465    hotel_season_id_seq    SEQUENCE     �   ALTER TABLE public.hotel_season ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    204            �            1259    16467    reservation    TABLE     L  CREATE TABLE public.reservation (
    id bigint NOT NULL,
    room_id integer,
    check_in_date date,
    total_price double precision,
    guest_count integer,
    guest_name character varying,
    guest_citizen_id character varying,
    guest_mail character varying,
    guest_phone character varying,
    check_out_date date
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    16473    reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    206            �            1259    16475    room    TABLE       CREATE TABLE public.room (
    id bigint NOT NULL,
    hotel_id integer NOT NULL,
    pension_id integer NOT NULL,
    season_id integer NOT NULL,
    type character varying NOT NULL,
    stock integer NOT NULL,
    adult_price double precision NOT NULL,
    child_price double precision NOT NULL,
    bed_capacity integer NOT NULL,
    square_meter integer NOT NULL,
    television boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    cash_box boolean NOT NULL,
    projection boolean NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    16481    room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    208            �            1259    16483    user    TABLE     x   CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    user_name text,
    user_pass text,
    user_role text
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16489    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    210            �          0    16446    hotel 
   TABLE DATA           �   COPY public.hotel (id, name, address, mail, phone, star, car_park, wifi, pool, fitness, concierge, spa, room_service) FROM stdin;
    public          postgres    false    200   (       �          0    16454    hotel_pension 
   TABLE DATA           C   COPY public.hotel_pension (id, hotel_id, pension_type) FROM stdin;
    public          postgres    false    202   z)       �          0    16462    hotel_season 
   TABLE DATA           M   COPY public.hotel_season (id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    204   �)       �          0    16467    reservation 
   TABLE DATA           �   COPY public.reservation (id, room_id, check_in_date, total_price, guest_count, guest_name, guest_citizen_id, guest_mail, guest_phone, check_out_date) FROM stdin;
    public          postgres    false    206   ,*       �          0    16475    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, pension_id, season_id, type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, cash_box, projection) FROM stdin;
    public          postgres    false    208   �*       �          0    16483    user 
   TABLE DATA           J   COPY public."user" (user_id, user_name, user_pass, user_role) FROM stdin;
    public          postgres    false    210   R+       �           0    0    hotel_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.hotel_id_seq', 51, true);
          public          postgres    false    201            �           0    0    hotel_pension_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.hotel_pension_id_seq', 17, true);
          public          postgres    false    203            �           0    0    hotel_season_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hotel_season_id_seq', 22, true);
          public          postgres    false    205            �           0    0    reservation_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.reservation_id_seq', 9, true);
          public          postgres    false    207            �           0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 18, true);
          public          postgres    false    209            �           0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 22, true);
          public          postgres    false    211            H           2606    16492     hotel_pension hotel_pension_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.hotel_pension
    ADD CONSTRAINT hotel_pension_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.hotel_pension DROP CONSTRAINT hotel_pension_pkey;
       public            postgres    false    202            F           2606    16494    hotel hotel_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    200            J           2606    16496    hotel_season hotel_season_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.hotel_season
    ADD CONSTRAINT hotel_season_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.hotel_season DROP CONSTRAINT hotel_season_pkey;
       public            postgres    false    204            L           2606    16498    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    206            N           2606    16500    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    208            P           2606    16502    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    210            �   T  x���MN�0�דS�P	��iʪ�E�*� $T��I��JbWN*��4܋�I�V*<[����^ԇ;]��#'�r��G*͂lB0q��QZP�s*�6=|�����S��ݠ�=�1g����Xm��z]�7��"k���6y���R�x�,F%@B�̲�*�b���U��NcN֬���Roo��y"�旭=?eo[��3&�8G��(2QP�DxY�E�D2��.`LuJ�6��>�4�$����n@�$p�u�m9:@�l�O�-�ځt8��\�|���1-L��]A|�w?�L��r�D���rd����Q&&������xH���J{S��QH����.6��Kq�A�~0��      �   W   x�34�41��V���Qp�q����Qp��Qpru��24�4� �q��������s�24�4�
���9�H�q�`������ dyZ      �   ;   x�3��41�4202�50"(���22�4��%g�ij�C�В��$`��3����qqq ���      �   �   x�=�K
�@D�=��$�7&;�Y�nD����l<��b�����k'�H*Z�I��z��v_�3���/p����v}�fp54g�֑�&��7s$H��6�[�����|*�Ƣ��ݲ�ا]�Rzu�#      �   �   x�U�A
1E�?��H�&vz��֭JA� ���J�B���|Q��������}����A����h^�^$���m-�3-[it'��d�e�=�	 3v��H�?�UP#9�w�A�r����7�}�:.      �   9   x�3�LL����4426�0�M8�RKJs b��9����\F��III����=... l�{     