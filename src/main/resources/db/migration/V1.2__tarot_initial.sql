CREATE TABLE if not exists tarot_card_type
(
    id          uuid PRIMARY KEY,
    name        varchar(100),
    description varchar(1000)
);

INSERT INTO tarot_card_type
VALUES ('b2108b4f-a59a-426a-baad-4121c7c0cef2', 'Major Arcana',
        'The Major Arcana Tarot cards reveal messages about the big picture of your life and its long-term direction'),
       ('0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'The Wands',
        'The Wands cards are about action, initiative, and invention. They help to guide how you move through your life, advising when to move and when to hold back'),
       ('3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'The Cups',
        'The Cups cards represent your emotions, intuition, and relationships. They can guide you in love and help you understand and process your highest and lowest feelings.'),
       ('2e3ff09c-2ecf-4f48-b754-eba46e953160', 'The Swords',
        'The Swords cards are all about challenges. They can tell you when conflict and heartache are looming and help you harness the strength of your own mind.'),
       ('22b1d652-5f5e-4987-81f6-b0785060a1b5', 'The Pentacles',
        'The Pentacles cards are associated with your work, finances, and domestic life. They can answer questions about your personal goals and your money, family, and health.');



CREATE TABLE if not exists tarot_card
(
    id                   uuid PRIMARY KEY,
    card_type_id         uuid references tarot_card_type (id),
    name                 varchar(100),
    description          varchar(2000),
    reversed_description varchar(2000),
    advice               varchar(1000),
    image_id             uuid
);

INSERT INTO tarot_card
VALUES ('ca9bc0e0-84bd-4a98-aab5-d4f241202590', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The Fool', 'Pamela Coleman-Smith''s artful rendition of The Fool in the Rider-Waite Tarot deck is often used to represent Tarot in general. Early classical versions of The Fool card, however, portray quite a different character -- a person driven by base needs and urges, who has fallen into a state of poverty and deprivation.

In some earlier instances, The Fool is made out to be a carnival entertainer or a huckster. In others, he is portrayed as decrepit and vulnerable -- as the cumulative result of his delusions and failures. Not until the 20th century do you see the popular Rider-Waite image of The Fool arise -- that of an innocent soul before its fall into matter, untainted by contact with society and all its ills.

Modern decks usually borrow from the Rider-Waite imagery. Most Fool cards copy the bucolic mountainside scene, the butterfly, and the potential misplaced step that will send The Fool tumbling into the unknown. Don''t forget, though, that the earlier versions of this card represented already-fallen humanity, over-identified with the material plane of existence, and beginning a pilgrimage toward self-knowledge and, eventually, wisdom. The Fool reminds us to recognize the path of personal development within ourselves -- and the stage upon that path where we find ourselves -- in order to energize our movement toward deeper self-realization.',
        'When the Fool card is reversed, you are encountering an unfinished side of yourself, a part still caught in the shadows of ignorance or immaturity. An emotional reflex or psychological attitude could be holding you back from responding authentically and naturally.

Release yourself from any dogmas or taboos so your natural truthfulness and instinct for right action can be restored.', 'The Fool advises that you lighten up. Let yourself be spontaneous enough to stretch beyond the realm of logic. There is no advantage to be gained by thinking you possess the knowledge, power, or control to direct reality. Open and receive without question, instead of trying to manage what''s happening right now. The Fool has no ambition to manipulate a specific outcome. Just be happy to be part of the whole.

Release any demands or expectations. Give your complete attention to events as they are occurring in the present moment.', null),
       ('8a542bb8-a664-41b6-87b8-8f9d925a1846', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The Magician', 'Traditionally, The Magician is one who can demonstrate hands-on magic -- as in healing, transformative rituals, alchemical transmutations, charging of talismans, and the like. A modern Magician is any person who completes the circuit between heaven and Earth; one who seeks to bring forth the divine gold within her or himself.

At the birth of Tarot, even a gifted healer who was not an ordained clergyman was considered to be in league with The Devil! For obvious reasons, the line between fooling the eye with sleight of hand and charging the world with magical will, was not clearly differentiated in the early Tarot cards.

Waite''s image of The Magician as the solitary ritualist communing with the spirits of the elements -- with its formal arrangement of symbols -- is a token of the freedom we have in modern times to declare our spiritual politics without fear of reprisal. The older cards were never so explicit about what The Magician was doing. It''s best to keep your imagination open with this card. Visualize yourself manifesting something unique, guided by evolutionary forces that emerge spontaneously from within your soul.',
        'The Magician card reversed suggests you may be working against your own creativity. Perhaps you feel that your ideas are too scandalous or too precedent breaking. Perhaps you feel it should be someone else who communicates these insights or challenges. Perhaps you are uncomfortable with taking a leadership position, even a temporary one.

Ask yourself what you have to lose. Have the courage of your convictions and speak your truth.', 'Have faith in your innate creativity. The Magician advises you give your questioning nature and free-associating mind plenty of room to explore the subject at hand. Behave just as if you were an open-minded and curious scientist. Through this process, you may bring freshness and clarity into the situation that is both stimulating and catalytic.

You do not have to understand it all intellectually. Besides, intuition is your ace in the hole. Respond in a spontaneous manner to what is right in front of you. There is no reason to hold yourself back. Your natural urges are exactly what is needed, and, your ingenuous timing and elegant style will help smooth over any awkwardness.',
        null),
       ('d00f71cc-9558-4385-ba75-909d0e0b10dc', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The High Priestess', 'The High Priestess is a Major Arcana, or "trump" card, that represents human wisdom. The High Priestess can be viewed as a kind of female Pope (a Papess), or the ancient Egyptian Priestess of Isis, the even more ancient snake and bird goddesses, the Greek goddess Persephone, or Eve, before the fall.

For the accused heretics who were burnt at the stake for revering her in the 14th and 15th century, the Priestess symbolized the prophecy of the return of the Holy Spirit, which was perceived as the female aspect of the Holy Trinity.

In terms of the Major Arcana ordering of cards, The High Priestess appears in the sequence as soon as the Fool decides he wants to develop his innate powers, making a move toward becoming a Magician. The High Priestess is his first teacher, representing the inner life and the method for contacting it, as well as the contemplative study of nature and spiritual mystery.',
        'The High Priestess card reversed suggests that when you have problems, it''s usually because you are so attracted to your inner life that it has become an addiction in itself. It blots out your interest in ordinary reality, friends, and family -- the connections that hold us fast to our worldly identities.

Don''t take this card as permission to abandon the world. This is a reminder to balance your outer identifications with internal cultivation, so you can more effectively bring the spiritual world to the material world.',
        'The High Priestess advises you to adhere to your chosen spiritual practice on a more regular basis. If you want the benefits of evolution, you''ll have to cooperate with spirit. We all have distractions, demands -- a whole life full of reasons why we cannot find the time to retreat into our inner sanctum.

Until you consider this as vital to your well-being as the need to eat and sleep, you are likely to be eternally restless and deeply dissatisfied. A spiritual routine that suits your temperament, practiced every day, is the most trustworthy path to freedom.',
        null),
       ('c7307582-7681-4445-b8c2-ba5201efb4c6', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The Empress', 'The Empress is a Major Arcana, or "trump" card, that portrays the energy of the great mother. She is nature, around us but also within us, the ever-unfolding source of life-giving power. The Empress is often pictured as a pre-Christian Goddess, as the one whom The High Priestess is channeling down to Earth for the rest of us.

In medieval Europe, The Empress card was painted to represent whatever queen currently ruled the land, probably to satisfy the Inquisitors. But the scholars of the Renaissance and beyond had no doubt of her true identity -- she could not be fully revealed on Tarot cards as "the woman clothed with the sun" until after the French Revolution.

This supreme archetype of femininity also symbolizes fertility. It is The Empress who provides us nourishment and security. She is also sometimes seen as delighting us with flowers and fruit. A potentially terrifying aspect of this archetype manifests itself whenever karmic mood swings wipe out our plans, like a storm that has come upon us. Whatever happens, The Empress is the source of our embodiment and of natural lLaw. She might even be called "The Great Recycler."',
        'Because the Empress represents Nature, when she is reversed she has tremendous natural power to correct her heedless children. Volcanoes, tsunami, hurricanes and the like symbolize violent emotions triggered by ignorant or foolish humans.

If you feel like being corrective, you are probably justified, but set limits on how punitive you allow yourself to be. You don''t want to go too far and destroy all you have taken so much delight in.',
        'The Empress advises you to trust in the good sense you have shown up to this point. Recognize your good intentions in carrying out your responsibilities as a compassionate human. You are capable of demonstrating the finest aspects of your personality.

Demonstrate this through caring actions, a forgiving and generous attitude, and wise understanding of others'' needs and struggles. Bring a healing influence to the current situation and take full credit for the supportive part you play.',
        null),
       ('3c06aa3d-50f7-473b-9f5b-6307e775423e', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The Emperor', 'In the most practical terms, The Emperor Tarot card represents the highest leadership, a head of state, or the most exemplary and powerful person in the realm. This archetypal ruler is responsible for the affairs of a society or community, which are directly proportional to his well being and happiness.

The more enlightenment and cosmic perspective this energy brings, the better life is for all. The Emperor archetype masters the world of matter and physical manifestation. When you apply this card to your situation, acknowledge your potentials for mastery. Reinforce a sense of sovereignty within yourself, despite any self-limiting beliefs, habits, or appearances to the contrary.',
        'When the Emperor card is reversed, there may be a tendency to behave like a petty tyrant, insisting on being indulged, served and flattered. This emperor prefers his own version of events to what is actually true and as such risks losing the respect of his people.

Try to curb whatever arrogance might be distorting your point of view. Remember that, though the emperor looks like the king, he is the servant of everyone in his realm. If he doesn''t serve well, the negative impact of his own defects are what will bring him down.', 'When the Emperor arises, he advises you get in touch with your inner sovereignty and natural self-possession. Realize that somewhere deep inside you is the memory of royalty. Reach within to find that strain of natural nobility and leadership. These innate qualities will help you manage your current situation.

The Emperor suggests that you have the necessary abilities to be the final authority. This situation is an opportunity to showcase your competence and skill. Act with the confidence of someone who knows how to take care of business.',
        null),
       ('b3f3e840-5fd3-400c-af5b-8148bdb007fc', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The Hierophant', 'The Hierophant Tarot this card refers to a master and the learning of practical lessons from the study of natural law. This energy of this card points to some agent or resource that can reveal the secrets of life, the cycles of the Moon and tides, the links between human beings and the heavens.

Because monasteries were the only places a person could learn to read and write in the Middle Ages, a Hierophant was one to whom a student would petition for entry. He was the one to set the curriculum for the neophyte''s course of study.

Often pictured with the right hand raised in blessing, the Hierophant is linked with the ancient lineage of Melchezidek, initiator of the Hebrew priestly tradition, the one who passes on the teachings. All shamans of any tradition draw upon this archetype.',
        'When the Hierophant card is reversed, this may be indicating that you bring an unnecessary episode of rebellion against traditional, long held ideals and spiritual beliefs that belong to your lineage. Be aware that without the steadying influence of tradition observed from generation to generation, there would be no stable support system within which you could afford to rebel.

Get a sense of whether you are biting the hand that feeds you. Take care lest you damage your right relationship to the Great Mystery.', 'The Hierophant advises that you return to the role of a meticulous student. Learn everything you can about your chosen area. Let that knowledge become a part of you and an operative influence on your day-to-day awareness. In this way, you can slowly and steadily establish real credibility in your field or chosen subject.

Earn respect and recognition by completing your education and broadening your experience. If you already have all the necessary experience you need, then rewrite your resume so others can appreciate who you are and what you can bring to a situation. Focus on your goal and be determined. You may be destined to be a master in your realm.',
        null),
       ('32cd1bf8-6552-4c6e-9658-e01d8d3309a9', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The Lovers', 'Although it has taken on a strictly romantic revision of meaning in some modern decks, traditionally The Lovers Tarot card reflected the challenges of choosing a partner. At a crossroads, one cannot take both paths. The images on this card in different decks have varied more than most, because we have had so many ways of looking at sex and relationships across cultures and centuries.

Classically, the energy of this card reminded us of the real challenges posed by romantic relationships, with the protagonist often shown in the act of making an either-or choice. To partake of a higher ideal often requires sacrificing the lesser option. The path of pleasure eventually leads to distraction from spiritual growth. The gratification of the personality eventually gives way to a call from spirit as the soul matures.

Modern decks tend to portray the feeling of romantic love with this card, showing Adam and Eve at the gates of Eden when everything was still perfect. This interpretation portrays humanity before the fall, and can be thought to imply a different sort of choice -- the choice of evolution over perfection, or the choice of personal growth through relationship -- instead of a fantasy where everything falls into place perfectly and is taken care of without effort.', 'The Lovers card reversed may indicate that you are going along with a divided situation because you have a vested interest in opposition. You can''t resolve this situation until you own up to your own double standard.

Admit the resistance you feel, instead of proceeding halfheartedly or with unspoken resentments. In such an important area of your life, you should not just go along to get along.', 'The Lovers card advises that you study your options and make the wisest choice. Carefully consider your long-term interests. There is no judgment on what you choose to keep from the array of possibilities before you. Just watch out for choices that will produce dissatisfaction and discontent.

Be willing to make some compromises, then stick with the commitments you finally make. Trust your intuition along with your rational intellect, and once you make your choice, carry it out with conviction.',
        null),
       ('c6f59f8e-beb5-41c7-881c-bb51d79c0d50', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The Chariot', 'The Chariot Tarot card points to a triumphal feeling of freedom, as if the Charioteer is being paraded through the streets as a hero (or heroine). The card reflects congratulations for high achievement, and serves as a sign of empowerment.

Huge wheels and frisky steeds speed the rate at which the driver''s will power can be realized. This kind of charge makes more of the world accessible to one ambitious enough to seize The Chariot''s reins. But there is danger in this feeling of freedom, because of the increased rate of change and its power to magnify mistakes in judgment. As a seasoned warrior, the Charioteer is called upon to be extra attentive to the way ahead.',
        'When this card is reversed, it suggests that you may balk when you know you need to pick up the reins and take responsibility for setting your life in motion.

You know you could instigate change, but you haven''t yet chosen to move toward it. You may be holding back for what feels like good reasons, but nothing is actually being imposed upon you; you do have other options. Own your own inner resistance instead of blaming circumstances.', 'The Chariot advises that you be prepared for changes that might include a move or an opportunity to travel. The Charioteer travels light and stays open to fresh experiences that change with every valley or mountain pass.

You may be asked to live out of a suitcase and consider every place your home for a while. Be receptive to new people who come into your life. Most especially, become more fluid and taste the joys of freedom. The Charioteer is nothing if not self-sufficient.

Be prepared and self-contained for the changes that will sweep in and carry you with them.', null),
       ('1e94f49d-14f9-47f4-891c-4c1cee25f60a', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'Strength', 'The Strength Tarot card represents nature, which, however wild in its primal form, is tamed by our subtler, finer self -- our feminine side, our inner self. The will and passion of our instinctive nature does not need to be broken, but refined and brought to consciousness, so that all levels of creation may come into harmony.

The feminine soul-force contains a persuasive power that can nurture and induce cooperation from others, stilling disruptive energies by harmonizing differences in the spirit of collective good will.', 'The Strength card reversed suggests that you are in a position where you have little power to affect behavior. No amount of modeling, persuasion or leadership will affect the out-of-control, untamed force that is set loose.

Subtleties are overlooked, and the inducements which have served in the past to reward order may meet with scorn. You may have to lay low and do what you can to preserve yourself, and let this upside down time blow over.',
        'The Strength card advises that you assertively discipline yourself and separate self interest from enlightened wisdom. Deliberately identify with your intuition, even if it works against the desires of your willful ego.

Demand and expect the same from others who have some power in this situation. You cannot challenge them to live to a higher standard if you, yourself, have not yet done so. Influence others by setting an example of integrity. Your self-esteem will increase to the degree that you succeed in your efforts.',
        null),
       ('14d68f83-70fe-4192-a91d-0fc12e1f87b5', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The Hermit', 'The challenge of The Hermit card is to be able to recognize a teacher in a humble disguise. This font of mysterious knowledge will not make it easy for the student to acquire his wisdom, as it takes time and long contemplation to fathom what he knows. He often speaks wordlessly, or in ancient and barbaric tongues, communicating with the elements, animals, and nature herself.

While an hourglass was an identifying feature on the earliest Hermit Tarot cards, more modern cards have shifted the metaphor, showing more or less light released from his lantern. In either case, the Hermit card reminds us of the value of time away from the hubbub of civic life, to relax the ego in communion with nature.',
        'When the Hermit card is reversed, you may be resisting the process of evolving towards a level of wisdom that sets you apart. Fear of the path is haunting you; it could be a fear of loneliness.

Try to remember that the shamanic method for facing fears is to relax and open. Let them in and allow them to have their way for a while. This way you know them fully and will see them pass of their own accord. When there are no more surprises, you can move into a place of empowered action.', 'The Hermit advises that you think things through carefully. The demands on you have been high, giving you scant time for reflection. While you have a gift for understanding the larger implications involved, you need some private time to consider the steps to take in the future. You can''t just lock yourself in your room for fifteen minutes and expect to come up with profound solutions. You need more seclusion and time to assimilate and process.

Now may be the moment for you to tell everyone to leave you alone. When you are fully ready, you will be able and willing to give others what they need.',
        null),
       ('00bcd993-0d68-4791-9123-f26dd23d8f74', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'Wheel of Fortune', 'The central theme of The Wheel of Fortune card is cyclical change. The wheel keeps on rolling, churning events in a ceaseless progression of ups and downs, either way freeing us from the past. No one can escape its cyclical action, which can feel somewhat terrifying -- no matter whether we are rising or falling. When one is balanced on top of the wheel, there is a moment of crystal clarity. However, the only part of the wheel that''s actually not going up and down is the hub, which represents your eternal self.

Every one of us will occupy all the points on the wheel at one point or another. The cycle of the wheel is its lesson -- and we can learn to take comfort in it. If you don''t like the look of things right now, just wait -- things will change. Of course, if you do like the look of things right now, enjoy it while it lasts, because that will change too!',
        'When the Wheel of Fortune card is reversed, it suggests that you may have dropped from the heights and have been sent back to the beginning -- to either start over or reframe your plan.

This may feel as if you have hit bottom, but think of it as a chance to renew yourself and chart a path that will help you rise from the ashes. You will be a more compassionate and wise ally when the next person takes a nosedive. In the future, you might want to be a little more modest in your aspirations.', 'The Wheel of Fortune advises you follow the flow of events. Physical moves, spiritual awakenings, or dramatically changing social patterns could arise now. Accept these transformations.

This is a safe place for you to be. You are watched over and protected as you go round and round the wheel. You will learn a lot. You will also learn it quickly, and what you absorb will benefit you for a long time to come.',
        null),
       ('7fd4f6ac-6856-4aa7-96cd-3792370ff177', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'Justice', 'The Justice Tarot card has to do with moral sensitivity and that which gives rise to empathy, compassion, and a sense of fairness. Since the time of Solomon, this image has represented a standard for the humane and fair-minded treatment of other beings.

Often including the image of a fulcrum or scale which helps to balance competing needs against the greater good, and a two-edged sword to symbolize the precision needed to make clear judgments, this card reminds us to be careful to attend to important details. It''s a mistake to overlook or minimize anything where this card is concerned. The law of karma is represented here -- what goes around comes around.', 'When the Justice card is reversed, the deeper meaning of this situation is being withheld for reasons only Higher Power truly understands. There are times that logical, common, rational and organic laws are overridden by some paradoxical higher order.

At such times you won''t be fully informed about what is going on. Wait, stay quiet, keep watching and listening. Eventually the chaos will resolve itself and the truth will be revealed.', 'The Justice card advises you to listen carefully as others explain to you their version of events and the parts they played. It is unnecessary to offer feedback. Your role is to observe, listen closely, give a full hearing and keep your wits about you as the story takes shape.

As you witness people''s account of themselves, your understanding will go beyond the words you hear. Subtle inferences and clues will reveal the truths that will enable you to make a wise and accurate assessment.',
        null),
       ('7c9a0e2b-1c64-4b8f-93ef-ceabd4822ea6', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The Hanged Man', 'The Hanged Man usually indicates a lack of ability to help oneself through independent action. The energy is arrested and awaiting judgment. With this card, there is no avenue for the will to regain control until the situation has passed.

This represents a good time to be philosophical, to study and meditate upon the position you find yourself in, and to form resolutions for the moment you become free again. Only those who possess wisdom, patience, and optimism will be able to see through limitations, including possible humiliation, to grasp the inspiring lesson one can gain from such an experience.', 'The Hanged Man reversed suggests that the seemingly noble deed of offering yourself as the sacrificial lamb is, at least for the time being, a useless gesture. There will be no benefit to the greater good; you will experience no expansion of consciousness.

It would be an empty move, so don''t put yourself in that position. Ask yourself on what basis you allowed yourself to hope that your sacrifice would make a difference in this situation. Who knows? Perhaps this is meant to be an opportunity to learn something about self-denying tendencies.', 'The Hanged Man advises you to surrender illusions of control. Accept that you have been overcome and deceived by your own devices. Having made your bed, you now need to lie in it. This is not a negative judgment. It''s just that sometimes there are consequences for being in the wrong place at the wrong time. Bad things can happen to good people. These consequences are not fatal, even if they are inconvenient -- or perhaps even embarrassing.

Stop resisting your circumstances and let some time go by. Eventually, you will be released a little wiser and not much the worse for wear. You will come to realize in time how you collaborated with the problem. However, the issues you were stuck on when you were first hung up have subsided and no longer concern you. You are free to take up new endeavors. You will ultimately feel refreshed and grateful that you were derailed from your former track.',
        null),
       ('ca682431-0ef8-4f1a-8b41-434761e0c55a', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'Death', 'The Death card is not about the literal death of any person. It may represent the death of something else, like a project, plan, or relationship. This card also points to a time of harvest, symbolized in classical decks by the reaping skeleton. Unless the fruits of summer are harvested, they are lost to winter''s harshness, and the people do not eat. As the scythe cuts the cords that link us to the past, it liberates us to go forward without fear, because we have nothing left to lose. Everything being pruned away is recycled for the fertility of the future, so that nothing is really ever lost, despite seasonal cycles of gain and loss.

In more modern Tarot decks, we see Death mounted on a horse and wearing black armor. The emphasis in these decks is on the punishment of sin, as in the way the medieval Plagu (which the Death image was based on) was used to explain the wrath of God. Luckily, in modern times, we are not so encumbered with such a guilt-ridden philosophy.', 'The Death card reversed suggests that you might long for the cord to be cut, but unfortunately you have to persist and endure without the relief of an ending. It is not time for termination and closure yet.

Be patient with the current circumstances without resigning yourself to a negative outcome. Coming changes may alter the way you feel about the status quo. Remember that harvest isn''t started until the fruit is ripe. Work at becoming wiser and more mellow, sweeter and more nourishing, and your time of release may happen sooner.', 'The Death card advises you to detach from the old order. You may want to close accounts, complete unfinished tasks, and gather your harvest. It is time to move on. If you cut the cords that have bound you to old ways and outdated conventions, you could free yourself to join the sweep of incoming light. This is not an excuse to reject others or hurt them in any way. It is simply a time to move toward your ultimate interests.

Do not allow nostalgia and outworn loyalties to hold you back. Be willing to go through whatever it takes to get to where you really want to be.',
        null),
       ('27ec9207-e4cb-4284-ae88-be7f8b7c5800', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'Temperance', 'The Temperance card is a reference to the soul. Classically female, the woman or angel on the Temperance card is mixing up a blend of subtle energies for the evolution of the personality. One key to interpreting this card can be found in its title -- a play on the process of tempering metals in a forge.

Metals must undergo extremes of temperature, folding, and pounding, but the end product is infinitely superior to impure ore mined from the earth. In this image, the soul volunteers the ego for a cleansing and healing experience, which may turn the personality inside-out, but which brings out the gold hidden within the heart.', 'The Temperance card reversed suggests that you are in danger of giving up on yourself. Something is making you feel apathetic and ignore what you deeply want and need.

You may be morbidly overemphasizing the chaos and disruption that would come with change and growth rather than focusing on the regeneration and liberation that could be yours. Try to carve out a day in which you do only activities that make you happy. Eat well and get some rest, then take another look at your life. If you do not gain a better perspective, seek help.', 'The Temperance card advises you to identify and seek the missing ingredients in your life. Marshal your known skills and abilities and do what needs to be done to complete your mission.

Prepare to use spiritual practices, studies, or lifestyle changes that can assist you in your quest. A tremendous amount of benefit is available if you can organize yourself and be disciplined at this time.',
        null),
       ('c5a37811-a6b1-4031-87ca-7c3e91a678de', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The Devil', 'The Devil Tarot card expresses the realm of the taboo -- the culturally rejected wildness and undigested shadow side that each of us carries in our subconscious. This shadow is actually at the core of our being, which we cannot get rid of and will never succeed in taming. From its earliest versions, which portrayed a vampire-demon, The Devil evoked the church-fueled fear that a person could "lose their soul" to wild and passionate forces.

The Devil image which emerged in the mid-1700s gives us a more sophisticated rendition -- that of the "scapegoated goddess," whose esoteric name is Baphomet. Volcanic reserves of passion and primal desire empower her efforts to overcome the pressure of stereotyped roles and experience true freedom of soul.', 'The Devil card reversed suggests that you are enjoying creating chaos and resistance for no particular, positive reason. Your sense of humor could be a bit twisted. Your motive may be vengeance or you are simply being an irritant.

The juvenile prankster streak in you must be corralled and changed before it gets you into further trouble. Suppress those impulses until you successfully engineer a shift away from this careless behavior. Such antics will cost you in the long run even though they seem amusing in the moment.',
        'The Devil card advises that you show some spunk. There may be nothing to be gained by trying to be subtle or strategic in this situation. Assert your agenda, express yourself honestly, and let the chips fall where they may. Your best bet could be to express your true emotions, possibly even including anger. Acknowledge that you have whatever feelings you have. While it may not be necessary to act out what you feel in every situation, accepting the power and depth of your inner experience enables you to remain true to yourself.',
        null),
       ('54458eb4-36b8-4ec5-80a1-b444cf7c9a7f', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The Tower', 'In practically all renditions of the Tower card, disaster is striking or has just struck. The demons of madness and despair are released from ancient hiding places, and nature conspires with human failings to destabilize a society. The upheaval is collective and impersonal. Let us remember these images were created for the educated nobles and clergy -- reminding them that they have the most to lose if the hierarchy is toppled.

Lightning is a fitting karmic payback for the guilt of those whose fortunes come from the exploitation or abuse of others. A modern subtitle might be "revolution," indicating that through drastic social change, oppressed people can find renewed hope of better times. The Tower experience comes like a flash of lightning to topple the hierarchy of the old order, after which everyone can have a fresh start on a more equal footing.', 'The Tower card reversed suggests that the drama is over. All the leaning towers have fallen. Leave behind the issues and emotions that caused this to happen.

Ask yourself what you can do now that the options of your past are closed. As you pick yourself up and begin again, you will find renewed energy for your next significant endeavor.', 'With the Tower card, think of yourself as an agent of transformation. This self-sacrificing role is likely to create stressful situations. Your vision shows you that a radical change has already been unleashed by forces much larger than mere mortals, and therefore you are no longer resisting.

Now you may be at the forefront, acknowledging and accepting the bracing presence of the future bursting in on the present. Try to mediate the harsher parts of the changes as they unfold, so the most vulnerable are the most cushioned. Acknowledge yourself, as well as the others in your life, who are offering their resources to usher in a better future.',
        null),
       ('5359cbab-eb25-4190-a8e9-d927bf97d77b', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The Star', 'The Star Tarot card is about reconnecting one''s soul with the divine -- the transcending of personality, family, community, and reputation. It has to do ultimately with the freedom to be one''s self. The soul is responding to celestial influences -- forces that can provide the personality with a stronger sense of purpose. The Star card helps us to remember our exalted origins and our attraction to a higher union.

This card could also be called The Celestial Mandate -- that which refers us back to our reason for being, our mission in this lifetime. The Star reminds us that, in a sense, we are agents of divine will in our day-to-day lives. If we let go of the idea that we are supposed to be in control, we can more easily notice and appreciate the synchronicities that are nudging us along. In this way, we become more conscious of the invisible helping hand, and we better understand our place within -- and value to -- the larger cosmos.', 'The Star card reversed suggests that you are temporarily alienated from your brilliance and usefulness. You may feel clumsy, unskilled, at odds within your true nature.

Perhaps you are forgetting your soul''s purpose in this lifetime. Focus on your unique gifts and talents -- their source is divine. It''s your job to learn how to apply them in any situation your life offers you.', 'The Star card advises that you rededicate yourself to your higher values, increase your spiritual cultivation and meditation practice, and surrender to the greater good. Connect to your higher self -- a being of a larger realm traveling on an evolutionary course that started long ago and runs indefinitely into the future. This is the part you wish to contact and communicate with.

Now is a period for quiet contemplation. Listen for the voice within. Anything that would interfere with this communion may not be serving your best interests right now.',
        null),
       ('80eb5578-9a1a-4176-93be-e30668df23fe', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The Moon',
        'The Moon card refers to a deep state of sensitivity and imaginative impressionability, developed within a womb of deep relaxation. Here we dream and go into trance, have visions and receive insights, wash in and out with the psychic tides, and experience deep mystical and/or terrifying realities beyond our ordinary senses. In a state of expanded consciousness, we cannot always control what happens. The Moon card represents the ultimate test of a soul''s integrity, where the membrane between the self and the unknown is removed, and the drop of individuality re-enters the ocean of being. What transpires next is between a soul and its maker.', 'The Moon card reversed suggests that you might be deluding yourself, exaggerating or embellishing your version of a situation. Consider whether you are repeating an emotional, dramatic rendition of events rather than keeping to the bare facts.

The temptation to let yourself be swept away emotionally or psychically is understandable, but it doesn''t help you find your balanced center in chaotic times.',
        'The Moon card advises that you trust your instincts and intuitions. Your intuitive body, which is connected to all living things, is sharper and quicker than the cultivated, civilized self. The everyday mind may not be prepared for strange oceanic circumstances. Plus, it has no game plan. Your intuitive body will support you unerringly if you do not interfere with or try to control what you perceive. A better approach would be to meditate. Try to just be a witness. Do nothing; let nature carry you forward. This may be your best option in this situation.',
        null),
       ('7a1e8c4e-7ff9-408e-8c86-bbb15324332a', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The Sun',
        'The Sun card is about the self -- who you are and how you cultivate your personality and character. The Sun''s radiance is where one''s original nature can be encountered in health and safety. The limitations of time and space are stripped away; the soul is refreshed and temporarily protected from the chaos outside the garden walls. Under the light of the Sun, life reclaims its primordial goodness, truth, and beauty. If one person is shown on this card, it is usually signifying a human incarnation of the divine. When two humans are shown, the image is portraying a resolution of the tension between opposites at all levels. It''s as if this card is saying "You can do no wrong -- it''s all to the good!"', 'The Sun card reversed suggests that this is one of two cards that traditionally have no negative meaning. Perhaps you will choose to take extra care to humbly count your blessings and give credit to all that have contributed to your successes.

Perhaps you can create a program of giving, volunteering or bringing as many people into your good fortune as you sensibly can. Satisfy your conscience that you are a wise steward of your good fortune.',
        'The Sun card advises you to have confidence in your natural divinity. Throw off any cultural conditioning that keeps you from being authentic with yourself. Step into the full light of truth and reveal your motives and principles. Once done, you will no longer give away power to the people that criticize and shame you. Focus on the positive and the real. Your authentic shining self can be a light for others if you project it without contrivance.',
        null),
       ('de6921ca-7b33-45fb-a3d7-0549f15cdfeb', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'Judgement', 'The Judgement card, sometimes called "Resurrection," represents the great reunion that the ancients believed would happen once in every age. This was the time when souls are harvested and taken home to their place of origin, outside the solar system. Then the world is seeded with a batch of new souls and the process starts over.

From a modern point of view, this great reunion -- which includes every personality that you have ever been and every soul that you have done deep work with -- reunites to consciously complete the process. In a way, we symbolically celebrate this returning to center every year on our birthday.

In personal terms, the Judgment Tarot card points to freedom from inner conflicts, and so clear a channel, that the buried talents and gifts of past incarnations can come through an individual in this lifetime. This card counsels you to trust the process of opening yourself, because what emerges is of consistently high quality. You can effortlessly manifest as a multi-dimensional being, and assist in evoking that response from others.', 'The Judgment card reversed suggests that you feel like you are bumping into externally imposed restrictions, even if you thought you had let go of limitations. If that''s the case, you need to accommodate those inexplicable blockages wherever they appear, without allowing the growth force rising within you to diminish.

Like water, you will find the openings and seep through, to move past whatever has been holding you back. Keep flowing and you will succeed.', 'The Judgment card advises that you allow yourself to grow, transform, and release hidden potentials within yourself. Divest yourself of fruitless endeavors without neglecting your duties. At the same time, invest your energies in new growth.

It''s not necessary to reject others, but refuse to be manipulated by those who cry foul. This process isn''t about them anyway. It is about you and the desire you feel to change your life and become a more complete person. Trust your impulses and allow this remarkable awakening to happen.',
        null),
       ('73a4f4af-7690-4ec3-805e-d9860672d8ff', 'b2108b4f-a59a-426a-baad-4121c7c0cef2', 'The World', 'The World card points to the presiding wisdom which upholds life on this and all worlds. In most Tarot decks, it is a female figure that has become our standard World image. She originates in Hebrew, Gnostic, and Alchemical lore, and stands between heaven and Earth as the cosmic mother of souls, the wife of God, and our protector from the karmic forces we have set loose upon the Earth in our immaturity and ignorance.

The goddess of The World card invites us into cosmic citizenship -- once we come to realize our soul''s potential for it. It announces the awakening of the soul''s immortal being, accomplished without the necessity of dying.

This card, like the Sun, is reputed to have no negative meaning no matter where or how it appears. If the Hermetic axiom is "Know Thyself", this image represents what becomes known when the true nature of self is followed to creative freedom and its ultimate realization.', 'The World card is one of only two cards that have no reverse meaning. Nevertheless, it could indicate a slight slowdown in the flow of events or the need for more introspection as events unfold.

Practice trusting and relaxing into the nurturing support of the Great Mother Goddess, while things sort themselves out.', 'The World card may be giving you permission to do whatever you want. Presently, your motivation is close to the will of the divine. Even if you commit an error, it will be turned to the greater good. Stay active and just keep moving forward. It is unnecessary to keep checking or interrupting your spontaneity with calculation. Rather than look for consensus or affirmation from others, simply dance the dance. In other words, express yourself, react naturally, and let the chips fall where they may.

What matters is divine intention. Whether or not human beings approve is less important. If you allow your ego to inflate, however, you cease to be useful to the greater plan.',
        null),
       ('0ab13fe7-5bcd-4d70-bd41-4d6b3bc200e8', '0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'Ace of Wands', '', '','', null),
       ('cd1bded9-21f2-4e7e-8c01-479f8097356e', '0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'Two of Wands', '', '','', null),
       ('26f5cc19-1fba-480f-b08c-18a022f437f6', '0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'Three of Wands', '', '','', null),
       ('3d0f2ec7-370c-401b-a611-f819d939b720', '0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'Four of Wands', '', '','', null),
       ('8e58d315-461f-4420-b083-b0d290269f91', '0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'Five of Wands', '', '','', null),
       ('d58dcd14-4cbb-4636-b989-d9f7d3aba000', '0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'Six of Wands', '', '','', null),
       ('c4c049f3-1167-41cd-a797-ce83e2bd0a9e', '0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'Seven of Wands', '', '','', null),
       ('7dc5095a-5162-4d16-8d2c-e65746c5909f', '0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'Eight of Wands', '', '','', null),
       ('9ea3ddf8-f167-4ce9-9cd3-49c3c3a285d9', '0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'Nine of Wands', '', '','', null),
       ('fdb2b5a4-906c-4489-940c-d30802a5c5cd', '0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'Ten of Wands', '', '','', null),
       ('df08bbc5-cc0a-4254-8368-9cf9a020400e', '0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'Page of Wands', '', '','', null),
       ('ebb2b930-7c08-4ec6-8953-04c9b3e30ba2', '0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'Knight of Wands', '', '','', null),
       ('213f6abf-1a55-439e-9d59-452f64a4b678', '0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'Queen of Wands', '', '','', null),
       ('971fff3d-e920-4907-9008-3b4021def62f', '0bc5eadd-ca5e-4cef-adb5-0f44ad40c383', 'King of Wands', '', '','', null),

       ('ce01f807-2322-4d3b-aea1-88a1f4ed19f3', '3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'Ace of Cups', '', '','', null),
       ('4e8a6531-686b-4fdd-a9ae-b569bfe77fff', '3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'Two of Cups', '', '','', null),
       ('e022aba8-489b-4e0e-a1b4-df806ebbaf4f', '3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'Three of Cups', '', '','', null),
       ('b002235d-1d86-425d-8cf0-f9cd7ea45f05', '3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'Four of Cups', '', '','', null),
       ('34b49319-5a57-45b4-8f14-21331fc850c5', '3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'Five of Cups', '', '','', null),
       ('2d26c08a-e353-400f-b308-1662386be3bb', '3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'Six of Cups', '', '', '',null),
       ('fb6c7ae6-63f7-4728-8cd1-dbaaa8d34abd', '3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'Seven of Cups', '', '', '',null),
       ('b93a5644-075b-4a04-b2cf-6a7f05dbe2db', '3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'Eight of Cups', '', '', '',null),
       ('07f33ceb-4778-4e44-ac69-d8afda61ae0d', '3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'Nine of Cups', '', '', '',null),
       ('078b2144-2b50-42c6-9cfd-d7eb4410643f', '3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'Ten of Cups', '', '', '',null),
       ('4c982e68-3359-4742-8da4-8e662665dfee', '3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'Page of Cups', '', '', '',null),
       ('701c3dbc-06ea-4f86-acb4-cab9348f7206', '3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'Knight of Cups', '', '', '',null),
       ('dae32b10-6ed5-4fbc-9a0e-f145c3d4a5d7', '3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'Queen of Cups', '', '','', null),
       ('091e8d31-241e-4020-ba71-970b63da0883', '3a6fc216-f492-4fc8-b185-7119ea84f3cc', 'King of Cups', '', '','', null),

       ('c52914ec-bf22-4b76-a08f-b04d78f10eb3', '2e3ff09c-2ecf-4f48-b754-eba46e953160', 'Ace of Swords', '', '','', null),
       ('a9f6addf-e2bb-4756-af08-8bf7d04417f4', '2e3ff09c-2ecf-4f48-b754-eba46e953160', 'Two of Swords', '', '','', null),
       ('e7c8b6f9-99a2-4d97-838d-36f694cfe017', '2e3ff09c-2ecf-4f48-b754-eba46e953160', 'Three of Swords', '', '','', null),
       ('fab55728-1859-491d-a37f-75c6380fc61e', '2e3ff09c-2ecf-4f48-b754-eba46e953160', 'Four of Swords', '', '', '',null),
       ('c84f9616-105a-4c2c-915d-954be432c3c6', '2e3ff09c-2ecf-4f48-b754-eba46e953160', 'Five of Swords', '', '', '',null),
       ('ab2748a0-7e95-4a22-83a8-c5726d5ccdfd', '2e3ff09c-2ecf-4f48-b754-eba46e953160', 'Six of Swords', '', '', '',null),
       ('a5a34593-10c2-458e-98f7-0c49bf678ad3', '2e3ff09c-2ecf-4f48-b754-eba46e953160', 'Seven of Swords', '', '', '',null),
       ('6634783c-7c02-442a-8f80-059fe9652857', '2e3ff09c-2ecf-4f48-b754-eba46e953160', 'Eight of Swords', '', '','', null),
       ('4c7f99b4-61b7-496c-9ed6-84f9d38c87ca', '2e3ff09c-2ecf-4f48-b754-eba46e953160', 'Nine of Swords', '', '', '',null),
       ('1df7e91c-d5a6-4a69-8e81-e49be8d38f90', '2e3ff09c-2ecf-4f48-b754-eba46e953160', 'Ten of Swords', '', '', '',null),
       ('b94b6fe2-143e-4ccb-9934-2849bdff5e25', '2e3ff09c-2ecf-4f48-b754-eba46e953160', 'Page of Swords', '', '', '',null),
       ('66de4e51-373b-47b9-85a3-d4c7e67aba6a', '2e3ff09c-2ecf-4f48-b754-eba46e953160', 'Knight of Swords', '', '', '',null),
       ('ed0949ca-533a-4ab0-9d42-66487ae43472', '2e3ff09c-2ecf-4f48-b754-eba46e953160', 'Queen of Swords', '', '', '',null),
       ('814ae180-7c7a-45a3-89d2-1177a92a5158', '2e3ff09c-2ecf-4f48-b754-eba46e953160', 'King of Swords', '', '','', null),

       ('35124d0e-1e2e-47ef-a7f8-4890c35103b3', '22b1d652-5f5e-4987-81f6-b0785060a1b5', 'Ace of Coins', '', '', '',null),
       ('e82e2c5b-04fe-4be7-ba38-6f08ab235e60', '22b1d652-5f5e-4987-81f6-b0785060a1b5', 'Two of Coins', '', '', '',null),
       ('ffef69f4-5563-492e-b7a7-d34db7772f31', '22b1d652-5f5e-4987-81f6-b0785060a1b5', 'Three of Coins', '', '','', null),
       ('22d69d1d-1e5b-44f1-86d0-114820957e25', '22b1d652-5f5e-4987-81f6-b0785060a1b5', 'Four of Coins', '', '', '',null),
       ('0570d100-ec7c-45d5-b829-19fdbfdcf52c', '22b1d652-5f5e-4987-81f6-b0785060a1b5', 'Five of Coins', '', '', '',null),
       ('d45652cc-644d-4f94-b058-74d6cccbd3fd', '22b1d652-5f5e-4987-81f6-b0785060a1b5', 'Six of Coins', '', '', '',null),
       ('908b6f1f-773d-4450-8947-18dda4b5be9f', '22b1d652-5f5e-4987-81f6-b0785060a1b5', 'Seven of Coins', '', '', '',null),
       ('ee04ae86-7e2e-4864-b9d6-9fdb5c227bf4', '22b1d652-5f5e-4987-81f6-b0785060a1b5', 'Eight of Coins', '', '', '',null),
       ('8119025f-0224-49be-b5ac-875e4566ea56', '22b1d652-5f5e-4987-81f6-b0785060a1b5', 'Nine of Coins', '', '', '',null),
       ('d9a8e50d-0a10-47d6-b348-2f23ab83fbc6', '22b1d652-5f5e-4987-81f6-b0785060a1b5', 'Ten of Coins', '', '', '',null),
       ('adf823ca-144b-4c53-9d66-b1ffe406d5e4', '22b1d652-5f5e-4987-81f6-b0785060a1b5', 'Page of Coins', '', '', '',null),
       ('47c5b985-79f5-4886-9d0f-98fbd6cb56e6', '22b1d652-5f5e-4987-81f6-b0785060a1b5', 'Knight of Coins', '', '','', null),
       ('031f6a50-455d-4fa8-9e29-14b18ec581ab', '22b1d652-5f5e-4987-81f6-b0785060a1b5', 'Queen of Coins', '', '', '',null),
       ('7f47b4ad-38d1-41f4-b686-2f4b3dd46715', '22b1d652-5f5e-4987-81f6-b0785060a1b5', 'King of Coins', '', '', '',null);
