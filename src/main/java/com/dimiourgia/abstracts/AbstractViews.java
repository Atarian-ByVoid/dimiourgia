package com.dimiourgia.abstracts;

public abstract class AbstractViews {
    public interface WithCreationDate {
    }

    public interface WithModificationDate {
    }

    public interface WithDeletionDate {
    }

    public interface WithTimestamps extends
            AbstractViews.WithCreationDate,
            AbstractViews.WithModificationDate,
            AbstractViews.WithDeletionDate {
    }

    public interface WithIdentifier {
    }

    public interface Paginate {
    }
}
