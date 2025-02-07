package CardAugments.cardmods.common;

import CardAugments.CardAugmentsMod;
import CardAugments.cardmods.AbstractAugment;
import CardAugments.patches.MultiPreviewFieldPatches;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class HolyMod extends AbstractAugment {
    public static final String ID = CardAugmentsMod.makeID(HolyMod.class.getSimpleName());
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    @Override
    public void onInitialApplication(AbstractCard card) {
        MultiPreviewFieldPatches.addPreview(card, new Miracle());
        card.cost = card.cost + 1;
        card.costForTurn = card.cost;
    }

    @Override
    public boolean canRoll(AbstractCard card) {
        AbstractCard upgradeCheck = card.makeCopy();
        upgradeCheck.upgrade();
        return card.cost == upgradeCheck.cost && validCard(card);
    }

    @Override
    public boolean validCard(AbstractCard card) {
        return card.cost >= 0 && card.type != AbstractCard.CardType.POWER;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToBot(new MakeTempCardInHandAction(new Miracle()));
    }

    @Override
    public String modifyName(String cardName, AbstractCard card) {
        return TEXT[0] + cardName + TEXT[1];
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + TEXT[2];
    }

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.COMMON;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new HolyMod();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}
