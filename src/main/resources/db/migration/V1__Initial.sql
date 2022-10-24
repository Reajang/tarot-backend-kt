CREATE TABLE if not exists tost
(
    id      uuid PRIMARY KEY,
    user_id uuid,
    text    varchar,
    common  boolean
);

INSERT INTO tost
VALUES ('ac5bac4f-6b33-4724-af67-ac39815684ac', null, '1', true),
       ('cb5206a5-708f-4e06-b814-6afd41d94944', null, '2', true),
       ('82557e49-4b46-45ba-85bb-520171f6be9f', null, '3', true),
       ('e0f26163-bde0-4247-ac39-9171aa6c2d25', null, '4', true),
       ('98619082-b7be-4101-8955-80f7b577c399', null, '5', true),
       ('afdb14bd-02ea-40e0-b430-dc1d284d6111', null, '6', true),
       ('669114a2-e796-49fb-b49c-8364464ff6d9', null, '7', true),
       ('941cc017-9842-41e5-ac2c-3f12e828f915', null, '8', true),
       ('4cec9e8c-e270-4683-ba37-78920b351fd2', null, '9', true),
       ('7157e58b-22e8-4534-8612-9b1079884cc3', null, '10', true),
       ('037feb86-42bc-468e-87fe-5a2650d073b0', null, '11', true),
       ('b24788b1-12d0-4966-9cbb-5c9fc6af6986', null, '12', true);
