CREATE TABLE category
(
    product_id bigint NOT NULL,
    scheme_id  bigint NOT NULL
);


ALTER TABLE category
    OWNER TO postgres;



CREATE TABLE orders
(
    id           bigint           NOT NULL,
    amount       double precision NOT NULL,
    date         timestamp without time zone,
    discount     double precision NOT NULL,
    payment_type integer          NOT NULL,
    ref          character varying(255),
    status       boolean          NOT NULL,
    total_amount double precision NOT NULL,
    user_id      bigint
);


CREATE SEQUENCE orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



ALTER SEQUENCE orders_id_seq OWNED BY orders.id;


CREATE TABLE orders_product_order
(
    orders_id        bigint NOT NULL,
    product_order_id bigint NOT NULL
);



CREATE TABLE preference
(
    id   bigint NOT NULL,
    name character varying(255)
);



CREATE SEQUENCE preference_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE preference_id_seq
    OWNER TO postgres;


ALTER SEQUENCE preference_id_seq OWNED BY preference.id;


CREATE TABLE price
(
    id     bigint           NOT NULL,
    amount double precision NOT NULL,
    method integer          NOT NULL,
    name   character varying(255),
    rule   integer          NOT NULL
);


CREATE SEQUENCE price_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



ALTER SEQUENCE price_id_seq OWNED BY price.id;



CREATE TABLE privilege
(
    id   bigint NOT NULL,
    name character varying(255),
    role character varying(255)
);


CREATE SEQUENCE privilege_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE privilege_id_seq OWNED BY privilege.id;


CREATE TABLE product
(
    id           bigint           NOT NULL,
    buy_price    double precision NOT NULL,
    product_code character varying(255),
    name         character varying(255),
    sell_price   double precision NOT NULL,
    unit         integer          NOT NULL,
    upc          character varying(255),
    scheme_id    bigint,
    vat_id       bigint
);



CREATE SEQUENCE product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE product_id_seq OWNED BY product.id;


CREATE TABLE product_order
(
    id         bigint           NOT NULL,
    discount   double precision NOT NULL,
    quantity   integer          NOT NULL,
    product_id bigint
);


CREATE SEQUENCE product_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_order_id_seq
    OWNER TO postgres;


ALTER SEQUENCE product_order_id_seq OWNED BY product_order.id;



CREATE TABLE scheme
(
    id          bigint                 NOT NULL,
    description character varying(255),
    name        character varying(255) NOT NULL,
    status      boolean                NOT NULL,
    defaultItem boolean                NOT NULL,
    type        integer                NOT NULL
);

CREATE SEQUENCE scheme_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



ALTER SEQUENCE scheme_id_seq OWNED BY scheme.id;



CREATE TABLE stock
(
    id         bigint  NOT NULL,
    quantity   integer NOT NULL,
    scheme_id  bigint,
    product_id bigint
);



CREATE TABLE stock_history
(
    id               bigint  NOT NULL,
    comment          character varying(255),
    date             date,
    quantity         integer NOT NULL,
    "time"           time without time zone,
    transaction_type integer NOT NULL,
    scheme_id        bigint,
    stock_id         bigint
);



CREATE SEQUENCE stock_history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



ALTER SEQUENCE stock_history_id_seq OWNED BY stock_history.id;


CREATE SEQUENCE stock_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE stock_id_seq OWNED BY stock.id;


CREATE TABLE tax
(
    id          bigint           NOT NULL,
    amount      double precision NOT NULL,
    amount_type integer          NOT NULL,
    beforevat   boolean          NOT NULL,
    type        integer          NOT NULL
);

CREATE SEQUENCE tax_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



ALTER SEQUENCE tax_id_seq OWNED BY tax.id;



CREATE TABLE taxes
(
    sale_id bigint NOT NULL,
    tax_id  bigint NOT NULL
);

CREATE TABLE users
(
    id           bigint  NOT NULL,
    address      character varying(255),
    email        character varying(255),
    last_name    character varying(255),
    name         character varying(255),
    phone_number integer NOT NULL,
    type         integer NOT NULL
);


CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



ALTER SEQUENCE users_id_seq OWNED BY users.id;


ALTER TABLE ONLY orders
    ALTER COLUMN id SET DEFAULT nextval('orders_id_seq'::regclass);



ALTER TABLE ONLY preference
    ALTER COLUMN id SET DEFAULT nextval('preference_id_seq'::regclass);



ALTER TABLE ONLY price
    ALTER COLUMN id SET DEFAULT nextval('price_id_seq'::regclass);

ALTER TABLE ONLY privilege
    ALTER COLUMN id SET DEFAULT nextval('privilege_id_seq'::regclass);

ALTER TABLE ONLY product
    ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);

ALTER TABLE ONLY product_order
    ALTER COLUMN id SET DEFAULT nextval('product_order_id_seq'::regclass);


ALTER TABLE ONLY scheme
    ALTER COLUMN id SET DEFAULT nextval('scheme_id_seq'::regclass);


ALTER TABLE ONLY stock
    ALTER COLUMN id SET DEFAULT nextval('stock_id_seq'::regclass);


ALTER TABLE ONLY stock_history
    ALTER COLUMN id SET DEFAULT nextval('stock_history_id_seq'::regclass);


ALTER TABLE ONLY tax
    ALTER COLUMN id SET DEFAULT nextval('tax_id_seq'::regclass);



ALTER TABLE ONLY users
    ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);



ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (product_id, scheme_id);



ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


ALTER TABLE ONLY preference
    ADD CONSTRAINT preference_pkey PRIMARY KEY (id);


ALTER TABLE ONLY price
    ADD CONSTRAINT price_pkey PRIMARY KEY (id);


ALTER TABLE ONLY privilege
    ADD CONSTRAINT privilege_pkey PRIMARY KEY (id);


ALTER TABLE ONLY product_order
    ADD CONSTRAINT product_order_pkey PRIMARY KEY (id);



ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


ALTER TABLE ONLY scheme
    ADD CONSTRAINT scheme_pkey PRIMARY KEY (id);

ALTER TABLE ONLY stock_history
    ADD CONSTRAINT stock_history_pkey PRIMARY KEY (id);


ALTER TABLE ONLY stock
    ADD CONSTRAINT stock_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tax
    ADD CONSTRAINT tax_pkey PRIMARY KEY (id);


ALTER TABLE ONLY orders_product_order
    ADD CONSTRAINT uk_7pv8ktxgla5mtfex0jxj0u593 UNIQUE (product_order_id);


ALTER TABLE ONLY product
    ADD CONSTRAINT uniquetypeandname UNIQUE (product_code, upc);



ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);



ALTER TABLE ONLY orders
    ADD CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id) REFERENCES users (id);


ALTER TABLE ONLY taxes
    ADD CONSTRAINT fk3bhwatq2bya11vd3h2v2s7gge FOREIGN KEY (tax_id) REFERENCES tax (id);


ALTER TABLE ONLY stock_history
    ADD CONSTRAINT fkc0fynmctuwama4iw63dxxovqp FOREIGN KEY (stock_id) REFERENCES stock (id);


ALTER TABLE ONLY orders_product_order
    ADD CONSTRAINT fkcsecjr6iu9dt5wk9hvn7vx9es FOREIGN KEY (product_order_id) REFERENCES product_order (id);


ALTER TABLE ONLY category
    ADD CONSTRAINT fkd6r9te0rn14njkuvuf2d59lq1 FOREIGN KEY (scheme_id) REFERENCES scheme (id);

ALTER TABLE ONLY product_order
    ADD CONSTRAINT fkh73acsd9s5wp6l0e55td6jr1m FOREIGN KEY (product_id) REFERENCES product (id);


ALTER TABLE ONLY stock
    ADD CONSTRAINT fkjghkvw2snnsr5gpct0of7xfcf FOREIGN KEY (product_id) REFERENCES product (id);


ALTER TABLE ONLY stock
    ADD CONSTRAINT fkk78uq78c3xlnbe7rwwc3myg5e FOREIGN KEY (scheme_id) REFERENCES scheme (id);


ALTER TABLE ONLY taxes
    ADD CONSTRAINT fkkxpnqgstv4u3rgh9h2s3imdsn FOREIGN KEY (sale_id) REFERENCES product (id);


ALTER TABLE ONLY stock_history
    ADD CONSTRAINT fklk4hobln2nd5914g5n2dhmbw8 FOREIGN KEY (scheme_id) REFERENCES scheme (id);



ALTER TABLE ONLY product
    ADD CONSTRAINT fkmkv22v68a2gyuab5sl45mu3pg FOREIGN KEY (scheme_id) REFERENCES scheme (id);



ALTER TABLE ONLY orders_product_order
    ADD CONSTRAINT fkpjlj8q3adgl52dtewgd4u1ky2 FOREIGN KEY (orders_id) REFERENCES orders (id);


ALTER TABLE ONLY category
    ADD CONSTRAINT fkqqm689b1x9dotoq6okskaxnx4 FOREIGN KEY (product_id) REFERENCES product (id);



ALTER TABLE ONLY product
    ADD CONSTRAINT fkrhfcb7sn0boihjemgg9qn82e1 FOREIGN KEY (vat_id) REFERENCES tax (id);
